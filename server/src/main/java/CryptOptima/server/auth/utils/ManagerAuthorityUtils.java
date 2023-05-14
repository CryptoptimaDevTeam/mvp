package CryptOptima.server.auth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// Authentication 객체에 담길 권한을 생성한다.
@Component
public class ManagerAuthorityUtils {
    private final List<String> KING_ROLES_STRING = List.of("KING","MASTER","MANAGER");
    private final List<String> MASTER_ROLES_STRING = List.of("MASTER","MANAGER");
    private final List<String> MANAGER_ROLES_STRING = List.of("MANAGER");

    public List<GrantedAuthority> createAuthoritiesByGrade(String grade) {
        if(grade.equals("KING")) return createAuthorities(KING_ROLES_STRING);
        else if(grade.equals("MASTER")) return createAuthorities(MASTER_ROLES_STRING);
        else return createAuthorities(MANAGER_ROLES_STRING);
    }

    // DB에 저장된 List<String> roles 를 기반으로 List<GrantedAuthority>를 생성한다.
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
        return authorities;
    }
}
