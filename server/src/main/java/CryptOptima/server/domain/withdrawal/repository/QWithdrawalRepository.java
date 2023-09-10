package CryptOptima.server.domain.withdrawal.repository;


import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;
import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static CryptOptima.server.domain.withdrawal.entity.QWithdrawalRecord.withdrawalRecord;

@Repository
@RequiredArgsConstructor
public class QWithdrawalRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    StringTemplate dateFormat = Expressions.stringTemplate(
            "DATE_FORMAT({0},{1})", withdrawalRecord.createdAt, ConstantImpl.create("%Y-%m-%d")
    );

    StringTemplate dateTimeFormat = Expressions.stringTemplate(
            "DATE_FORMAT({0},{1})", withdrawalRecord.createdAt, ConstantImpl.create("%Y-%m-%d %T")
    );

    // Todo Entity 반환 & Mapper 전환
    public List<WithdrawalDto.UserWithdrawal> findUserWithdrawals(
            Long userId, Long exchangeId, String status, LocalDateTime startDate, LocalDateTime endDate, int size, Long ltWithdrawalId) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.UserWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId, withdrawalRecord.exchangeId,
                        withdrawalRecord.usdt, withdrawalRecord.txid, withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(eqUserId(userId),
                        eqExchangeId(exchangeId),
                        eqStatus(status),
                        ltWithdrawalRecordId(ltWithdrawalId),
                        withdrawalRecord.createdAt.between(startDate, endDate))
                .orderBy(withdrawalRecord.withdrawalRecordId.desc())
                .limit(size)
                .fetch();
    }

    public List<WithdrawalDto.MngWithdrawal> findMngWithdrawals(
            Long userId, Long exchangeId, String status, LocalDateTime startDate, LocalDateTime endDate, int size, Long ltWithdrawalId) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.MngWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId, withdrawalRecord.user.userId, withdrawalRecord.user.accountId,
                        withdrawalRecord.exchangeId, withdrawalRecord.usdt, withdrawalRecord.txid, withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(eqUserId(userId),
                        eqExchangeId(exchangeId),
                        eqStatus(status),
                        ltWithdrawalRecordId(ltWithdrawalId),
                        withdrawalRecord.createdAt.between(startDate, endDate))
                .orderBy(withdrawalRecord.withdrawalRecordId.desc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression eqUserId(Long userId) {
        if (userId == null) return null;
        return withdrawalRecord.user.userId.eq(userId);
    }

    private BooleanExpression eqExchangeId(Long exchangeId) {
        if(exchangeId==null) return null;
        return withdrawalRecord.exchangeId.eq(exchangeId);
    }

    private BooleanExpression eqStatus(String status) {
        if(status == null) return null;
        return withdrawalRecord.withdrawalStatus.eq(WithdrawalRecord.Status.valueOf(status));
    }

    private BooleanExpression ltWithdrawalRecordId(Long withdrawalId) {
        if(withdrawalId == null) return null;
        return withdrawalRecord.withdrawalRecordId.lt(withdrawalId);
    }
}
