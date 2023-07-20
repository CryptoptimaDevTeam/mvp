package CryptOptima.server.security.managerdetails;

import CryptOptima.server.domain.manager.Manager;
import CryptOptima.server.domain.manager.ManagerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        roles.add(new SimpleGrantedAuthority("ROLE_MANAGER"));

        ManagerDetails managerDetails = new ManagerDetails(findManager, roles);

        return managerDetails;
    }
}
