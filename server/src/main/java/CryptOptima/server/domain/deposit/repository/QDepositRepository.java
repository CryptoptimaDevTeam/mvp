package CryptOptima.server.domain.deposit.repository;

import CryptOptima.server.domain.deposit.dto.DepositDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static CryptOptima.server.domain.deposit.entity.QDepositRecord.depositRecord;

@Repository
@RequiredArgsConstructor
public class QDepositRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    // dateFormat
    StringTemplate dateFormat = Expressions.stringTemplate(
            "DATE_FORMAT({0},{1})", depositRecord.createdAt, ConstantImpl.create("%Y-%m-%d")
    );

    StringTemplate dateTimeFormat = Expressions.stringTemplate(
            "DATE_FORMAT({0},{1})", depositRecord.createdAt, ConstantImpl.create("%Y-%m-%d %T")
    );

    // TODO Projection 적용 vs Entity -> DTO 전환 실행 시간 비교하기.
    // Projection의 경우, UserDeposit과 MngDeposit을 리턴하는 메서드를 따로 둬야 함.
    // DTO 전환의 경우, Mapper에서 DateTimeFormat 으로 전환해야 함.

    // TODO springboot scheduler를 활용한 startDate, endDate 갱신
    public List<DepositDto.UserDeposit> findUserDepositsByUserIdAndExchangeIdAndCoinIdAndDate(
            Long userId, Long exchangeId, Long coinId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.UserDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.coinId, depositRecord.coinPrice,
                        depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
                        .and(depositRecord.exchangeId.eq(exchangeId))
                        .and(depositRecord.coinId.eq(coinId))
//                        .and(dateFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.UserDeposit> findUserDepositsByUserIdAndExchangeIdAndDate(
            Long userId, Long exchangeId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.UserDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.coinId, depositRecord.coinPrice,
                        depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
                        .and(depositRecord.exchangeId.eq(exchangeId))
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.UserDeposit> findUserDepositsByUserIdAndCoinIdAndDate(
            Long userId, Long coinId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.UserDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.coinId, depositRecord.coinPrice,
                        depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
                        .and(depositRecord.coinId.eq(coinId))
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.UserDeposit> findUserDepositsByUserIdAndDate(
            Long userId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.UserDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.coinId, depositRecord.coinPrice,
                        depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByUserIdAndExchangeIdAndCoinIdAndDate(
            Long userId, Long exchangeId, Long coinId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .where(depositRecord.user.userId.eq(userId)
                        .and(depositRecord.exchangeId.eq(exchangeId))
                        .and(depositRecord.coinId.eq(coinId))
//                        .and(dateFormat.between(startDate, endDate))
                        )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByUserIdAndExchangeIdAndDate(
            Long userId, Long exchangeId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
                        .and(depositRecord.exchangeId.eq(exchangeId))
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByUserIdAndCoinIdAndDate(
            Long userId, Long coinId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                        depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
                                .and(depositRecord.exchangeId.eq(coinId))
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByUserIdAndDate(
            Long userId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                        depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.user.userId.eq(userId)
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByExchangeIdAndCoinIdAndDate(
            Long exchangeId, Long coinId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.exchangeId.eq(exchangeId)
                        .and(depositRecord.coinId.eq(coinId))
//                        .and(dateTimeFormat.between(startDate, endDate))
                )
                .fetch();
    }
    public List<DepositDto.MngDeposit> findDepositsByExchangeIdAndDate(
            Long exchangeId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.exchangeId.eq(exchangeId)
//                        .and(dateTimeFormat.between(startDate,endDate))
                )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByCoinIdAndDate(
            Long coinId/*, String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                        depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(depositRecord.coinId.eq(coinId)
//                        .and(dateTimeFormat.between(startDate,endDate))
                )
                .fetch();
    }

    public List<DepositDto.MngDeposit> findDepositsByDate(
            /*String startDate, String endDate*/) {
        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.accountId,
                        depositRecord.coinId, depositRecord.coinPrice,depositRecord.depositAmount,depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(
//                        dateTimeFormat.between(startDate,endDate))
                )
                .fetch();
    }
}
