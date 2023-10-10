package CryptOptima.server.domain.userexchange.repository;

import CryptOptima.server.domain.userexchange.entity.UserExchange;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;

import static CryptOptima.server.domain.userexchange.entity.QUserExchange.userExchange;

@Repository
@RequiredArgsConstructor
public class QUserExchangeRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public List<UserExchange> findUserExchanges(int size, Long userId, Long exchangeId, Long lastUserExchangeId) {
        return jpaQueryFactory
                .selectFrom(userExchange)
                .where(ltUserExchangeId(lastUserExchangeId).and(userIdEq(userId)).and(exchangeIdEq(exchangeId)))
                .orderBy(userExchange.userExchangeId.desc())
                .limit(size)
                .fetch();
    }

    public UserExchange findUserExchangeByUserIdAndExchangeId(Long userId, Long exchangeId) {
        return jpaQueryFactory
                .selectFrom(userExchange)
                .where(userExchange.user.userId.eq(userId).and(userExchange.exchange.exchangeId.eq(exchangeId)))
                .fetchOne();
    }

    private BooleanExpression ltUserExchangeId(Long lastUserExchangeId) {
        return lastUserExchangeId==null ? null : userExchange.userExchangeId.lt(lastUserExchangeId);
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? userExchange.user.userId.eq(userId) : null;
    }

    private BooleanExpression exchangeIdEq(Long exchangeId) {
        return exchangeId != null ? userExchange.exchange.exchangeId.eq(exchangeId) : null;
    }
}

