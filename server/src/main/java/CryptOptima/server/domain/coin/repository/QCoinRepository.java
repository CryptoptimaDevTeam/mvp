package CryptOptima.server.domain.coin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import static CryptOptima.server.domain.coin.entity.QCoin.coin;

@Repository
@RequiredArgsConstructor
public class QCoinRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public String findCoinPriceByCoinId(Long coinId) {
        return jpaQueryFactory.select(coin.price)
                .from(coin)
                .where(coin.coinId.eq(coinId))
                .fetchOne();
    }
}
