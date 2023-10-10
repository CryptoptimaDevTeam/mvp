package CryptOptima.server.security.handler;

import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.mail.MailService;
import CryptOptima.server.security.jwt.JwtService;
import CryptOptima.server.security.jwt.JwtTokenizer;
import CryptOptima.server.security.utils.OAuth2AttributeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final MailService mailService;
    private final String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        Map<String, Object> attributes = OAuth2AttributeUtils.getAttributes(token);

        User user;

        try { // userRepository 에서 email 으로 User를 찾는다.
            user = userRepository.findByAccountId((String) attributes.get("email")).orElseThrow(
                    () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        } catch (BusinessLogicException e) { // 존재한다면 넘어가고, 없다면 User를 저장한다.
            user = userRepository.save(
                    User.builder()
                            .registrationId((String) attributes.get("registrationId"))
                            .accountId((String) attributes.get("email"))
                            .username((String) attributes.get("name"))
                            .locale((String) attributes.get("locale"))
                            .status("ACTIVE")
                            .paybackCumAmount("0")
                            .paybackTotalRequestedAmount("0")
                            .paybackFinishedAmount("0") // todo 칼럼 기본 값 설정
                            .referralCode(getReferralCode(6)) // todo 레퍼럴 코드 길이 설정
                            .build()
            );

            mailService.sendWelcomeMail(user);
        }

        // AT, RT를 발급한다.
        String accessToken = JwtTokenizer.generateAccessToken(user);
        String refreshToken = JwtTokenizer.generateRefreshToken(user);

        // redis에 RT 저장
        jwtService.login((String) attributes.get("email"), refreshToken);

        String uri = UriComponentsBuilder.fromUriString("/users/login")
                .queryParam("access", "Bearer" + accessToken)
                .queryParam("refresh", refreshToken)
                .build()
                .toUriString();

        getRedirectStrategy().sendRedirect(request,response,uri);
    }

    private String getReferralCode(int length) {
        Random random = new Random();

        while(true) {
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(charset.length());
                char randomChar = charset.charAt(randomIndex);
                sb.append(randomChar);
            }

            String referralCode = sb.toString(); // 랜덤 코드 발급

            if(!userRepository.findByReferralCode(referralCode).isPresent()) {
                return referralCode;
            }
        } // 랜덤 코드 중복 X라면 종료
    }
}