package CryptOptima.server.auth.oauth.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class OAuth2CustomUser implements OAuth2User, Serializable {

    private Map<String, Object> attributes;
    private List<GrantedAuthority> authorities;
    private String accountId;
    private Long userId;

    private OAuth2CustomUser(Long userId, String accountId, List<GrantedAuthority> authorities) {
        this.userId = userId;
        this.accountId = accountId;
        this.authorities = authorities;
    }

    public static OAuth2CustomUser of(Long userId, String accountId, List<GrantedAuthority> authorities) {
        return new OAuth2CustomUser(userId, accountId, authorities);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.accountId;
    }

    public Long getUserId() { return this.userId; }
}
