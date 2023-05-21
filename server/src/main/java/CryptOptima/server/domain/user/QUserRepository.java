package CryptOptima.server.domain.user;

import CryptOptima.server.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import static CryptOptima.server.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class QUserRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public User findUserByUserId(Long userId) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchOne();
    }
}
