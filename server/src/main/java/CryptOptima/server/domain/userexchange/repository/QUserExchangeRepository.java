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

    public List<UserExchange> findAllUserExchanges(int size, Long lastUserExchangeId) {
        return jpaQueryFactory
                .selectFrom(userExchange)
                .where(ltUserExchangeId(lastUserExchangeId))
                .orderBy(userExchange.userExchangeId.desc())
                .limit(size)
                .fetch();
    }

    public List<UserExchange> findUserExchangesByUserId(int size, Long userId, Long lastUserExchangeId) {
        return jpaQueryFactory
                .selectFrom(userExchange)
                .where(ltUserExchangeId(lastUserExchangeId).and(userExchange.user.userId.eq(userId)))
                .orderBy(userExchange.userExchangeId.desc())
                .limit(size)
                .fetch();
    }

    public List<UserExchange> findUserExchangesByExchangeId(int size, Long exchangeId, Long lastUserExchangeId) {
        return jpaQueryFactory
                .selectFrom(userExchange)
                .where(ltUserExchangeId(lastUserExchangeId).and(userExchange.exchange.exchangeId.eq(exchangeId)))
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

    // TODO JOIN을 이용한 필요 칼럼만 뽑아오기

    private BooleanExpression ltUserExchangeId(Long lastUserExchangeId) {
        return lastUserExchangeId==null ? null : userExchange.userExchangeId.lt(lastUserExchangeId);
    }

}

