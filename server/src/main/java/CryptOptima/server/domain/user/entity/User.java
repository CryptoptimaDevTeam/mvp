package CryptOptima.server.domain.user.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import CryptOptima.server.domain.deposit.entity.DepositRecord;
import CryptOptima.server.domain.userexchange.entity.UserExchange;
import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User
 * Google, Facebook, Twitter 로그인 user 엔티티
 */
@Entity(name = "User")
@DynamicInsert
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String accountId; // 회원 이메일(아이디)

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String status; // 회원 상태(active, dormant, ban)

    @OneToMany(mappedBy = "user")
    private List<UserExchange> userExchangeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<DepositRecord> depositRecords = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WithdrawalRecord> withdrawalRecords = new ArrayList<>();

    // USDT를 기준으로 한 누적입금액
    @Column(nullable = false)
    @ColumnDefault("0")
    private String paybackCumAmount;

    // USDT를 기준으로 한 환급완료액
    @Column(nullable = false)
    @ColumnDefault("0")
    private String paybackFinishedAmount;

    // USDT를 기준으로 한 환급신청액
    @Column(nullable = false)
    @ColumnDefault("0")
    private String paybackTotalRequestedAmount;

    // 누적입금액을 증액한다. : deposit create 때
    public void plusPaybackCumAmount(String amount) {
        BigDecimal cumAmount = new BigDecimal(this.paybackCumAmount);
        BigDecimal addAmount = new BigDecimal(amount);
        this.paybackCumAmount = cumAmount.add(addAmount).toString();
    }

    // 누적환급액을 증액한다. : withdrawal 요청 -> 완료
    public void plusPaybackFinishedAmount(String amount) {
        BigDecimal finAmount = new BigDecimal(this.paybackFinishedAmount);
        BigDecimal addAmount = new BigDecimal(amount);
        this.paybackFinishedAmount = finAmount.add(addAmount).toString();
    }

    // 총 출금요청액을 증액한다. : withdrawal 요청 create
    public void plusPaybackTotalRequestedAmount(String amount) {
        BigDecimal totalReqAmount = new BigDecimal(paybackTotalRequestedAmount);
        BigDecimal addAmount = new BigDecimal(amount);
        this.paybackTotalRequestedAmount = totalReqAmount.add(addAmount).toString();
    }

    // 총 출금요청액을 차감한다 : withdrawal 요청 -> 완료/취소
    public void minusPaybackTotalRequestedAmount(String amount) {
        BigDecimal totalReqAmount = new BigDecimal(paybackTotalRequestedAmount);
        BigDecimal subAmount = new BigDecimal(amount);
        this.paybackTotalRequestedAmount = totalReqAmount.subtract(subAmount).toString();
    }

    // 출금요청액이 출금가능액에 대해 유효한지 검증한다.
    public boolean isWithdrawalPossible(String amount) {
        BigDecimal cumAmount = new BigDecimal(this.paybackCumAmount);
        BigDecimal finAmount = new BigDecimal(this.paybackFinishedAmount);
        BigDecimal withdrawalPossibleAmount = cumAmount.subtract(finAmount);

        BigDecimal totalReqAmount = new BigDecimal(this.paybackTotalRequestedAmount);
        BigDecimal withdrawalAmount = new BigDecimal(amount);

        if(withdrawalPossibleAmount.subtract(totalReqAmount).compareTo(withdrawalAmount)==-1) {
            throw new BusinessLogicException(ExceptionCode.EXCEEDED_THE_WITHDRAWAL_LIMIT);
        }
        else return true;
        // 출금가능액 - 총 출금요청액 < 현재 요청액 -> false
    }

    // 사용자의 접속 ip주소 리스트를 넣어두기 -> 추후 사용자 분석 데이터로 활용
}
