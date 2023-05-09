package CryptOptima.server.domain.withdrawal;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class WithdrawalRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalRecordId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long exchangeId;

    @Column(nullable = false)
    private String usdtAmount;

    @Column
    private String txid; // 송금 후 업데이트하기

    @Column(nullable = false) // TODO Enum 필드의 필요성이 있는가?
    private String withdrawalStatus; // required pending(txid update) complete cancel fail

    // 출금내역 상태를 변경한다.
    public void changeWithdrawalStatus(String newWithdrawalStatus) {
        this.withdrawalStatus = newWithdrawalStatus;
    }

    // 관리자페이지에서 출금 승인 후 txid를 추가한다.
    public void insertTxid(String txid) {
        this.txid = txid;
    }
}
