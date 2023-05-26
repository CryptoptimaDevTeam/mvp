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
@DynamicInsert
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
    @ColumnDefault("requested")
    private String withdrawalStatus; // required pending(txid update) complete cancel fail

    // 출금내역 상태를 변경한다.
    public void changeWithdrawalStatus(String newWithdrawalStatus) {
        this.withdrawalStatus = newWithdrawalStatus;
    }

    // 관리자페이지에서 출금 승인 후 txid를 추가한다.
    public void changeTxid(String txid) {
        this.txid = txid;
    }

//    TODO enum과 상태 field 사용법
//    private enum Status {
//        REQUESTED(),PROCESSING,COMPLETE,CANCELED;
//
//        private String status;
//        public Status(String status) {
//            this.status = status;
//        }
//    }
}
