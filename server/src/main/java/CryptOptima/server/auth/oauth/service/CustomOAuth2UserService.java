package CryptOptima.server.auth.oauth.service;

import CryptOptima.server.domain.user.User;
import CryptOptima.server.domain.user.UserRepository;
import CryptOptima.server.auth.oauth.dto.OAuth2CustomUser;
import CryptOptima.server.auth.oauth.dto.OAuthAttributes;
import CryptOptima.server.auth.oauth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final UserAuthorityUtils authorityUtils;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        Map<String, Object> originAttributes = oAuth2User.getAttributes();  // OAuth2User의 attribute

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    // 소셜 정보를 가져옵니다.

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, originAttributes);
        User user = saveOrUpdate(attributes);
        String accountId = user.getAccountId();
        List<GrantedAuthority> authorities = authorityUtils.createAuthoritiesByGrade("USER");

        return new OAuth2CustomUser(registrationId, originAttributes, authorities, accountId);
    }

    private User saveOrUpdate(OAuthAttributes authAttributes) {
        log.info("email = {}", authAttributes.getEmail());
        User user = userRepository.findByAccountId(authAttributes.getEmail())
                .orElse(authAttributes.toEntity());
        return userRepository.save(user);
    }
}
