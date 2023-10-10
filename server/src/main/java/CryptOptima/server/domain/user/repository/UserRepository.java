package CryptOptima.server.domain.user.repository;

import CryptOptima.server.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
    Optional<User> findByReferralCode(String referralCode);
}
