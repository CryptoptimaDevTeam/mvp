package CryptOptima.server.domain.withdrawal.repository;

import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends JpaRepository<WithdrawalRecord, Long> {
}
