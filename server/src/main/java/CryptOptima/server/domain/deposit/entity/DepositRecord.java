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

    @Column(nullable = false)
    private Long exchangeId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user; // TODO uid를 통해 user를 찾아내는 작업 필요

    @Column(nullable = false)
    private Long coinId; // 입금 코인 단위

    @Column(nullable = false)
    private String depositAmount; // 입금액

    @Setter
    @Column(nullable = false)
    private String coinPrice; // 코인의 USDT 환산 금액

    @Column(nullable = false)
    private String usdt; // USDT로 환산한 입금액. KST 09:00를 기준으로 계산한다.

    public void changeUsdt() {
        BigDecimal coinPrice = new BigDecimal(this.coinPrice);
        BigDecimal depositAmount = new BigDecimal(this.depositAmount);

        this.usdt = (coinPrice.multiply(depositAmount)).toString();
    }
}
