package CryptOptima.server.domain.exchange.repository;

import CryptOptima.server.domain.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

}
