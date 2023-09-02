package CryptOptima.server.domain.role.repository;

import CryptOptima.server.domain.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
