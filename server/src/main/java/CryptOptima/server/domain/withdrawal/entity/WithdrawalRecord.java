package CryptOptima.server.domain.withdrawal.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.global.event.AlertEvent;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalRecord extends BaseTimeEntity implements AlertEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalRecordId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false) // TODO exchange 연관관계 맺기
    private Long exchangeId;

    @Column(nullable = false)
    private String usdt;

    @Column
    private String txid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status withdrawalStatus = Status.REQUESTED;

    @Column(nullable = false)
    private String paybackCumAmount; // 당시 누적입금액

    @Column(nullable = false)
    private String paybackFinishedAmount; // 당시 누적환급액

    public enum Status {
        REQUESTED, PENDING, COMPLETE, FAILED, CANCELED;
    }

    @PrePersist
    public void setWithdrawalStatus() {
        this.withdrawalStatus = this.withdrawalStatus == null ? Status.REQUESTED : this.withdrawalStatus;
    }

    /**
     * complete: 총 환급 요청 금액을 차감, 누적 페이백 금액을 증액
     * failed, canceled: 총 환급요청 금액을 차감
     * @param newWithdrawalStatus
     */

    public void changeWithdrawalStatus(Status newWithdrawalStatus) {

        switch(newWithdrawalStatus) {
            case COMPLETE:
                this.withdrawalStatus = newWithdrawalStatus;
                this.user.minusPaybackTotalRequestedAmount(this.usdt);
                this.user.plusPaybackFinishedAmount(this.usdt);

                this.paybackCumAmount = this.user.getPaybackCumAmount();
                this.paybackFinishedAmount = this.user.getPaybackFinishedAmount();
                break;

            default:
                this.withdrawalStatus = newWithdrawalStatus;
                this.user.minusPaybackTotalRequestedAmount(this.usdt);

                this.paybackCumAmount = this.user.getPaybackCumAmount();
                this.paybackFinishedAmount = this.user.getPaybackFinishedAmount();
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

    public void plusPaybackTotalReqAmount() { // create 시 호출됨.
        this.user.plusPaybackTotalRequestedAmount(this.usdt);
        this.paybackCumAmount = this.user.getPaybackCumAmount();
        this.paybackFinishedAmount = this.user.getPaybackFinishedAmount();
    }
}
