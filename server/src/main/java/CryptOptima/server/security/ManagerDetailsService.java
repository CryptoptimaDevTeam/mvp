package CryptOptima.server.security;

import CryptOptima.server.domain.manager.Manager;
import CryptOptima.server.domain.manager.ManagerRepository;
import CryptOptima.server.security.utils.ManagerAuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ManagerDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;
    private final ManagerAuthorityUtils authorityUtils;

    public ManagerDetailsService(ManagerRepository managerRepository, ManagerAuthorityUtils authorityUtils) {
        this.managerRepository = managerRepository;
        this.authorityUtils = authorityUtils;
    }

    /**
     * @param accountId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Optional<Manager> optionalManager = managerRepository.findManagerByAccountId(accountId);
        Manager findManager = optionalManager.orElseThrow(() -> new UsernameNotFoundException("해당 Id를 갖는 매니저가 존재하지 않습니다."));

        return new ManagerDetails(findManager);
        // ManagerDetails(UserDetails) = username + password + authorities 를 리턴함.
    }

    private final class ManagerDetails extends Manager implements UserDetails {

        ManagerDetails(Manager manager) {
            setManagerId(manager.getManagerId());
            setAccountId(manager.getAccountId());
            setPassword(manager.getPassword());
            setManagerName(manager.getManagerName());
            setManagerGrade(manager.getManagerGrade());
            setRoles(manager.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getAccountId();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
