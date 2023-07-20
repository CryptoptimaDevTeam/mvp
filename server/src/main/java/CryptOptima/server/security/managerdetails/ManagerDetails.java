package CryptOptima.server.security.managerdetails;

import CryptOptima.server.domain.manager.Manager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ManagerDetails extends User {

    private final Manager manager;

    public ManagerDetails(Manager manager, Collection<? extends GrantedAuthority> authorities) {
        super(manager.getAccountId(), manager.getPassword(), authorities);
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }
}
