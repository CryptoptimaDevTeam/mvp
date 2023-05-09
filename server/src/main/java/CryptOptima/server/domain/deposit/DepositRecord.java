package CryptOptima.server.domain.deposit;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * DepositRecord
 * 거래소로부터 당사 계좌로 들어온 referral fee 입금 Entity
 */

// TODO createdAt 칼럼만 남기기

@Entity
public class DepositRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositRecordId;

    @Column(nullable = false)
    private Long exchangeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String symbol; // 입금 코인 단위

    @Column(nullable = false)
    private String depositAmount; // 입금액

    @Column(nullable = false)
    private String usdt; // USDT로 환산한 입금액. KST 09:00를 기준으로 계산한다.

    // 거래소, 회원 별 정렬
}
