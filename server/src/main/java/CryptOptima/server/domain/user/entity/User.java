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

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Users")
@DynamicInsert
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false)
    private String registrationId;

    @Column(nullable = false, unique = true)
    private String accountId; // 회원 이메일(아이디)

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String status; // 회원 상태(active, dormant, ban)

    @Column(nullable = false)
    private String locale;

    @Column(nullable = false, unique = true)
    private String referralCode;

    @OneToMany(mappedBy = "user")
    private List<UserExchange> userExchangeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepositRecord> depositRecords = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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
            throw new BusinessLogicException(ExceptionCode.EXCEEDED_WITHDRAWAL_LIMIT);
        }
        else return true;
        // 출금가능액 - 총 출금요청액 < 현재 요청액 -> false
    }

    // 현재는 변동가능 attribute 중 username만 취급한다.
    private boolean isSame(User user) {
        return this.username.equals(user.getUsername());
    }

    // OAuth2 attribute 변동사항 검사를 위한 메서드, loadUser에서 사용 됨.
    public void updateUser(User user) {
        // 현재는 username에 대해서만 다룬다.
        if(!isSame(user)) this.username = user.getUsername();
    }

    public void changeStatus(String status) {
        this.status = status;
    }
    // 사용자의 접속 ip주소 리스트를 넣어두기 -> 추후 사용자 분석 데이터로 활용

}
