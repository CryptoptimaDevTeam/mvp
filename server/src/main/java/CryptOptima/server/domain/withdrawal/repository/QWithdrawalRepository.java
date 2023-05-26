package CryptOptima.server.domain.withdrawal.repository;


import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

    public List<WithdrawalDto.UserWithdrawal> findUserWithdrawalsByUserIdAndExchangeIdAndDate(
            Long userId, Long exchangeId/*, String startDate, String endDate*/) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.UserWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId, withdrawalRecord.exchangeId,
                        withdrawalRecord.usdt, withdrawalRecord.txid, withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(withdrawalRecord.user.userId.eq(userId)
                        .and(withdrawalRecord.exchangeId.eq(exchangeId))
//                        .and(dateFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<WithdrawalDto.UserWithdrawal> findUserWithdrawalsByUserIdAndDate (
            Long userId/*, String startDate, String endDate*/) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.UserWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId, withdrawalRecord.exchangeId,
                        withdrawalRecord.usdt, withdrawalRecord.txid, withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(withdrawalRecord.user.userId.eq(userId)
//                        .and(dateFormat.between(startDate, endDate))
                )
                .fetch();
    }

    public List<WithdrawalDto.MngWithdrawal> findMngWithdrawalsByUserIdAndExchangeIdAndDate(
            Long userId, Long exchangeId/*, String startDate, String endDate*/){

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.MngWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId,withdrawalRecord.user.userId, withdrawalRecord.user.accountId,
                        withdrawalRecord.exchangeId, withdrawalRecord.usdt, withdrawalRecord.txid,withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(withdrawalRecord.user.userId.eq(userId)
                        .and(withdrawalRecord.exchangeId.eq(exchangeId))
//                        .and(dateFormat.between(startDate,endDate))
                )
                .fetch();
    }

    public List<WithdrawalDto.MngWithdrawal> findMngWithdrawalsByUserIdAndDate (
            Long userId/*, String startDate, String endDate*/) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.MngWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId,withdrawalRecord.user.userId, withdrawalRecord.user.accountId,
                        withdrawalRecord.exchangeId, withdrawalRecord.usdt, withdrawalRecord.txid,withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(withdrawalRecord.user.userId.eq(userId)
//                        .and(dateFormat.between(startDate,endDate))
                )
                .fetch();    }

    public List<WithdrawalDto.MngWithdrawal> findMngWithdrawalsByExchangeIdAndDate (
            Long exchangeId/*, String startDate, String endDate*/) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.MngWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId,withdrawalRecord.user.userId, withdrawalRecord.user.accountId,
                        withdrawalRecord.exchangeId, withdrawalRecord.usdt, withdrawalRecord.txid,withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
                .where(withdrawalRecord.exchangeId.eq(exchangeId)
//                        .and(dateFormat.between(startDate,endDate))
                )
                .fetch();
    }

    public List<WithdrawalDto.MngWithdrawal> findMngWithdrawalsByDate (
            /*String startDate, String endDate*/) {

        return jpaQueryFactory.select(Projections.constructor(WithdrawalDto.MngWithdrawal.class,
                        withdrawalRecord.withdrawalRecordId,withdrawalRecord.user.userId, withdrawalRecord.user.accountId,
                        withdrawalRecord.exchangeId, withdrawalRecord.usdt, withdrawalRecord.txid,withdrawalRecord.withdrawalStatus, dateTimeFormat))
                .from(withdrawalRecord)
//                .where(dateFormat.between(startDate,endDate))
                .fetch();
    }

}
