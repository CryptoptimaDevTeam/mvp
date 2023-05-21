package CryptOptima.server.domain.deposit.repository;

import CryptOptima.server.domain.deposit.entity.DepositRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositRecord, Long> {
}
