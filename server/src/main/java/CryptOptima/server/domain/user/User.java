package CryptOptima.server.domain.user;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User
 * Google, Facebook 로그인 user 엔티티
 */
@Entity
public class User extends BaseTimeEntity {
    // user 엔티티 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String userAccountId; // 회원 이메일(아이디)

    @Column(nullable = false)
    private String status; // 회원 상태(active, dormant, ban)

    @OneToMany(mappedBy = "user")
    private List<UserExchange> userExchangeList = new ArrayList<>();

    // USDT를 기준으로 한 누적입금액
    @Column(nullable = false)
    private String paybackCumAmount;

    // USDT를 기준으로 한 환급완료액
    @Column(nullable = false)
    private String paybackFinishedAmount;

    // USDT를 기준으로 한 환급신청액
    @Column(nullable = false)
    private String paybackTotalRequestedAmount;

    // 누적입금액을 증액한다.
    public void plusPaybackCumAmount(String amount) {
        BigDecimal cumAmount = new BigDecimal(paybackCumAmount);
        BigDecimal addAmount = new BigDecimal(amount);
        cumAmount.add(addAmount);
        this.paybackCumAmount = cumAmount.toString();
    }

    // 누적환급액을 차감한다.
    public void minusPaybackCumAmount(String amount) {
        BigDecimal cumAmount = new BigDecimal(paybackCumAmount);
        BigDecimal subAmount = new BigDecimal(amount);
        cumAmount.subtract(subAmount);
        this.paybackCumAmount = cumAmount.toString();
    }

    // 총 출금요청액을 증액한다.
    public void plusPaybackTotalRequestedAmount(String amount) {
        BigDecimal totalReqAmount = new BigDecimal(paybackTotalRequestedAmount);
        BigDecimal addAmount = new BigDecimal(amount);
        totalReqAmount.add(addAmount);
        this.paybackTotalRequestedAmount = totalReqAmount.toString();
    }

    // 총 출금요청액을 차감한다.
    public void minusPaybackTotalRequestedAmount(String amount) {
        BigDecimal totalReqAmount = new BigDecimal(paybackTotalRequestedAmount);
        BigDecimal subAmount = new BigDecimal(amount);
        totalReqAmount.subtract(subAmount);
        this.paybackTotalRequestedAmount = totalReqAmount.toString();
    }

    // 사용자의 접속 ip주소 리스트를 넣어두기 -> 추후 사용자 분석 데이터로 활용
}
