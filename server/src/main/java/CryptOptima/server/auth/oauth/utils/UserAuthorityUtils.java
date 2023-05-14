package CryptOptima.server.auth.oauth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// TODO 추후 user 등급 생성 시 변경 가능.
@Component
public class UserAuthorityUtils {
    private final List<String> USER_ROLES_STRING = List.of("USER");

    public List<GrantedAuthority> createAuthoritiesByGrade(String grade) {
        if(grade.equals("USER")) return createAuthorities(USER_ROLES_STRING);
        else return null;
    }

    // DB에 저장된 List<String> roles 를 기반으로 List<GrantedAuthority>를 생성한다.
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
        return authorities;
    }
}
