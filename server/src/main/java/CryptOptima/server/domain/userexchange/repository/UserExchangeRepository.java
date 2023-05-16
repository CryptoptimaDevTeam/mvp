package CryptOptima.server.domain.userexchange.repository;

import CryptOptima.server.domain.userexchange.entity.UserExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExchangeRepository extends JpaRepository<UserExchange, Long> {

}