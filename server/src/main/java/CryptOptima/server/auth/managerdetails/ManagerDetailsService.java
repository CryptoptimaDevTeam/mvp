package CryptOptima.server.auth.managerdetails;

import CryptOptima.server.auth.utils.AuthorityUtils;
import CryptOptima.server.domain.manager.Manager;
import CryptOptima.server.domain.manager.ManagerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManagerDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    public ManagerDetailsService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
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
    }
}
