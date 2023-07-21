package CryptOptima.server.security.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

// Authentication 객체에 담길 권한을 생성한다.
public class CustomAuthorityUtils {

    public static List<GrantedAuthority> createAuthoritiesByRole(String role) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        switch (role) {
            case "MANAGER":
                authorityList.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            case "USER":
                authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorityList;
    }
}
