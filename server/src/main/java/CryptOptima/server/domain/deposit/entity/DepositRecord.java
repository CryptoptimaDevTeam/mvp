package CryptOptima.server.domain.deposit.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import CryptOptima.server.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * DepositRecord
 * 거래소로부터 당사 계좌로 들어온 referral fee 입금 Entity
 */

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositRecordId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long exchangeId;

    @Column(nullable = false)
    private Long coinId; // 입금 코인 단위

    @Column(nullable = false)
    private String depositAmount; // 입금액

    @Setter
    @Column(nullable = false)
    private String coinPrice; // 코인의 USDT 환산 금액

    @Column(nullable = false)
    private String usdt; // USDT로 환산한 입금액. KST 09:00를 기준으로 계산한다.

    @Column(nullable = false)
    private String paybackCumAmount; // 당시 누적입금액

    @Column(nullable = false)
    private String paybackFinishedAmount; // 당시 누적환급액

    // TODO 사용자 기준 시각 칼럼 추가하기

    public void changeUsdt() {
        BigDecimal coinPrice = new BigDecimal(this.coinPrice);
        BigDecimal depositAmount = new BigDecimal(this.depositAmount);

        this.usdt = (coinPrice.multiply(depositAmount)).toString();
    }

    public void plusPaybackCumAmount() {
        this.user.plusPaybackCumAmount(this.usdt);
    }
}
