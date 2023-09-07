package CryptOptima.server.domain.deposit.repository;

import CryptOptima.server.domain.deposit.dto.DepositDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static CryptOptima.server.domain.deposit.entity.QDepositRecord.depositRecord;

@Repository
@RequiredArgsConstructor
public class QDepositRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    StringTemplate dateFormat = Expressions.stringTemplate(
            "DATE_FORMAT({0},{1})", depositRecord.createdAt, ConstantImpl.create("%Y-%m-%d")
    );

    StringTemplate dateTimeFormat = Expressions.stringTemplate(
            "DATE_FORMAT({0},{1})", depositRecord.createdAt, ConstantImpl.create("%Y-%m-%d %T")
    );


    public List<DepositDto.UserDeposit> findUserDeposits(Long userId, Long exchangeId, Long coinId,
                                                         LocalDateTime startDate, LocalDateTime endDate, int size, Long ltDepositId) {

        return jpaQueryFactory.select(Projections.constructor(DepositDto.UserDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.coinId, depositRecord.coinPrice,
                        depositRecord.depositAmount, depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(eqUserId(userId),
                        eqCoinId(coinId),
                        eqExchangeId(exchangeId),
                        ltDepositRecordId(ltDepositId),
                        depositRecord.createdAt.between(startDate, endDate))
                .orderBy(depositRecord.depositRecordId.desc())
                .limit(size)
                .fetch();
    }

    public List<DepositDto.MngDeposit> findMngDeposits(Long userId, Long exchangeId, Long coinId,
                                                       LocalDateTime startDate, LocalDateTime endDate, int size, Long ltDepositId) {

        return jpaQueryFactory.select(Projections.constructor(DepositDto.MngDeposit.class,
                        depositRecord.depositRecordId, depositRecord.exchangeId, depositRecord.user.userId, depositRecord.user.username,
                        depositRecord.coinId, depositRecord.coinPrice, depositRecord.depositAmount, depositRecord.usdt, dateTimeFormat))
                .from(depositRecord)
                .where(eqUserId(userId),
                        eqCoinId(coinId),
                        eqExchangeId(exchangeId),
                        ltDepositRecordId(ltDepositId),
                        depositRecord.createdAt.between(startDate, endDate))
                .orderBy(depositRecord.depositRecordId.desc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression eqUserId(Long userId) {
        if(userId==null) return null;
        return depositRecord.user.userId.eq(userId);
    }

    private BooleanExpression eqExchangeId(Long exchangeId) {
        if(exchangeId==null) return null;
        return depositRecord.exchangeId.eq(exchangeId);
    }

    private BooleanExpression eqCoinId(Long coinId) {
        if(coinId==null) return null;
        return depositRecord.coinId.eq(coinId);
    }

    private BooleanExpression ltDepositRecordId(Long depositRecordId) {
        if(depositRecordId == null) return null;
        return depositRecord.depositRecordId.lt(depositRecordId);
    }

}