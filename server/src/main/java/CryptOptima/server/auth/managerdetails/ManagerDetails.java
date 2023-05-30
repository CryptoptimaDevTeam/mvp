package CryptOptima.server.auth.managerdetails;

import CryptOptima.server.domain.manager.Manager;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ManagerDetails extends Manager implements UserDetails {

    private Long managerId;
    private String accountId;
    private List<GrantedAuthority> authorities;

    // loadUserByUsername 에서 사용
    ManagerDetails(Manager manager) {
        setManagerId(manager.getManagerId());
        this.managerId = manager.getManagerId();

        setAccountId(manager.getAccountId());
        this.accountId = manager.getAccountId();

        setPassword(manager.getPassword());
        setManagerName(manager.getManagerName());
        setManagerGrade(manager.getManagerGrade());

        setRoles(manager.getRoles());
        this.authorities = manager.getRoles().stream().map(
                role -> new SimpleGrantedAuthority("ROLE_"+role)
        ).collect(Collectors.toList());
    }

    private ManagerDetails(Long managerId, String accountId, List<GrantedAuthority> authorities) {
        this.managerId = managerId;
        this.accountId = accountId;
        this.authorities = authorities;
    }

    public static ManagerDetails of(Long managerId, String accountId, List<GrantedAuthority> authorities) {
        return new ManagerDetails(managerId, accountId, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
