package CryptOptima.server.auth.oauth.service;

import CryptOptima.server.auth.utils.AuthorityUtils;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.auth.oauth.dto.OAuth2CustomUser;
import CryptOptima.server.auth.oauth.dto.OAuthAttributes;
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
    private final AuthorityUtils authorityUtils;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        Map<String, Object> originAttributes = oAuth2User.getAttributes();  // OAuth2User의 오리지널 attribute

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    // 소셜 정보를 가져온 후
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, originAttributes);  // sns 별 공통 attribute로 추출한다.

        User user = saveOrUpdate(attributes, registrationId);
        String accountId = user.getAccountId(); // DB에 저장한 객체의 email
        Long userId = user.getUserId(); // DB에 저장한 객체의 userId(PK)

        List<GrantedAuthority> authorities = authorityUtils.createAuthoritiesByGrade("USER");

        return new OAuth2CustomUser(originAttributes, authorities, accountId, userId);
    }

    private User saveOrUpdate(OAuthAttributes authAttributes, String registrationId) {
        User user = authAttributes.toEntity(registrationId); // 새로 생성한 user

        User findUser = userRepository.findByAccountId(authAttributes.getEmail())
                .orElseGet(() -> userRepository.save(user));   // DB에 없다면 새로 생성한 user를 저장한다.

        findUser.updateUser(user); // 기존 user(findUser)와 새로 생성한 user가 다르다면, 업데이트 한다.
        return findUser; // orElseGet 구문에 의해 새롭게 생성된 user 이거나, updateUser에 의해 업데이트 된 user 가 된다.
    }
}
