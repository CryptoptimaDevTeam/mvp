package CryptOptima.server.domain.withdrawal.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import CryptOptima.server.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalRecordId;

    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Long exchangeId;

    @Column(nullable = false)
    private String usdt;

    @Column
    private String txid; // TODO null 처리하기 & 송금 후 업데이트하기

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status withdrawalStatus = Status.REQUESTED;

    public enum Status {
        REQUESTED, PENDING, COMPLETE, FAILED, CANCELED;
    }

    @PrePersist
    public void setWithdrawalStatus() {
        this.withdrawalStatus = this.withdrawalStatus == null ? Status.REQUESTED : this.withdrawalStatus;
    }

    // 출금내역 상태를 변경한다.
    public void changeWithdrawalStatus(Status newWithdrawalStatus) {

        switch(newWithdrawalStatus) {
            case COMPLETE:
                this.withdrawalStatus = newWithdrawalStatus;
                this.user.minusPaybackTotalRequestedAmount(this.usdt);
                this.user.plusPaybackFinishedAmount(this.usdt);
                break;

            case FAILED, CANCELED:
                this.withdrawalStatus = newWithdrawalStatus;
                this.user.minusPaybackTotalRequestedAmount(this.usdt);
                break;
        }
    }

    // 관리자페이지에서 출금 승인 후 txid를 추가한다.
    public void changeTxid(String txid) {
        // PENDING 상태변경이 다른 상태변경들과 달리 txid도 함께 업데이트 해야하기 때문에 책임 범위가 다르다고 판단하여 따로 뺌.
        this.withdrawalStatus = Status.PENDING;
        this.txid = txid;
    }

    // 출금요청액이 출금가능액에 대해 유효한지 검증
    public void isWithdrawalPossible() {
        this.user.isWithdrawalPossible(this.usdt);
    }

    public void plusPaybackTotalReqAmount() {
        this.user.plusPaybackTotalRequestedAmount(this.usdt);
    }


//    public void minusPaybackTotalReqAmount() {
//        this.user.minusPaybackTotalRequestedAmount(this.usdt);
//    }

//    public void plusPaybackFinishedAmount() {
//        this.user.plusPaybackFinishedAmount(this.usdt);
//    }
}
