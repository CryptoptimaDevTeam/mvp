package CryptOptima.server.security.managerdetails;

import CryptOptima.server.domain.manager.entity.Manager;
import CryptOptima.server.domain.manager.repository.ManagerRepository;
import CryptOptima.server.security.utils.CustomAuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    public ManagerDetailsService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {

        Manager findManager = managerRepository.findManagerByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        // todo 추후 manager role 세분화 시 리팩토링. ElementCollection 해제하기.
        List<GrantedAuthority> roles = CustomAuthorityUtils.createAuthoritiesByRole("MANAGER");

        ManagerDetails managerDetails = new ManagerDetails(findManager, roles);

        return managerDetails;
    }
}
