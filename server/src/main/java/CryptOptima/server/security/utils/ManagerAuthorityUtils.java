package CryptOptima.server.security.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// Authentication 객체에 담길 권한을 생성한다.
@Component
public class ManagerAuthorityUtils {
    // TODO managerGrade에 따른 Role 목록 추가

    // DB에 저장된 List<String> roles 를 기반으로 List<GrantedAuthority>를 생성한다.
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
        return authorities;
    }
}
