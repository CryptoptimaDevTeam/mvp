package CryptOptima.server.security.jwt;

import CryptOptima.server.domain.manager.ManagerRepository;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.security.dto.TokenDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private final RedisTemplate<String, String> redisTemplate;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;

    ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void login(String accountId, String refreshToken) { // SuccessHandler onAuthenticationSuccess
        // 0. OAuth2 or UsernamePassword 인증
        // 1. 이미 로그인 했는지 검증
        if(valueOperations.get(accountId) == null) {
            // 만료시각 = expiration time
            valueOperations.set(accountId, refreshToken, Duration.ofDays(90L));
        } else {
            redisTemplate.delete(accountId);
            valueOperations.set(accountId, refreshToken, Duration.ofDays(90L));
//            throw new BusinessLogicException(ExceptionCode.DUPLICATED_LOGIN); TODO 기존 로그인 해제 및 새로운 로그인 허용 여부 결정
        }
    }

    @Override
    public void access(String accessToken) { // JwtVerificationFilter doFilterInternal
        // 1. AT 조회
        if (valueOperations.get(accessToken) != null) {
            // 2. 있으면 Exception
            throw new BusinessLogicException(ExceptionCode.ACCESS_TOKEN_NOT_VALID);
        }
    }

    @Override
    public TokenDto.ReissueResponse reissue(String accountId, String refreshToken) { // Controller 특정 유저에게 발급된 RT가 맞는지 조회
        // 1. refreshToken 조회
        String findRefreshToken = valueOperations.get(accountId);
        if(findRefreshToken == null) throw new BusinessLogicException(ExceptionCode.REFRESH_TOKEN_NOT_EXISTS);
        if(!findRefreshToken.equals(refreshToken)) throw new BusinessLogicException(ExceptionCode.REFRESH_TOKEN_NOT_VALID);

        // 2. AT 발급
        Claims claims = JwtTokenizer.parseClaims(refreshToken).getBody();
        String accessToken = reissueAccessToken(accountId, claims.getSubject());

        // 3. RT 잔여시간이 2주 미만이라면 삭제 & 재발급
        refreshToken = reissueRefreshToken(findRefreshToken, claims, accountId);
//
        return TokenDto.ReissueResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void logout(String accountId, String accessToken, String refreshToken) { // Controller
        // 0. AT 검증
        // 1. AT 등록
        valueOperations.set(accessToken,"logout", getTTL(accessToken));
        // 2. RT 삭제
         redisTemplate.delete(accountId);
    }

    // AT의 잔여 시간을 반환한다.
    private Duration getTTL(String accessToken) {
        Date expiration = JwtTokenizer.parseClaims(accessToken).getBody().getExpiration();
        long ttl = Duration.between(Instant.now(), expiration.toInstant()).toMillis();

        return Duration.ofMillis(ttl);
    }

    // subject에 따라 AccessToken을 발급한다.
    private String reissueAccessToken(String accountId, String subject) {

        switch (subject) {
            case "USER":
                return JwtTokenizer.generateAccessToken(
                        userRepository.findByAccountId(accountId).orElseThrow(
                                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND))
                );

            default:
                return JwtTokenizer.generateAccessToken(
                        managerRepository.findManagerByAccountId(accountId).orElseThrow(
                                () -> new BusinessLogicException(ExceptionCode.MANAGER_NOT_FOUND))
                );
        }
    }

    // RT 잔여 시간에 따라 그대로 리턴 or 재발급한다.
    private String reissueRefreshToken(String refreshToken, Claims claims, String accountId) {
        Date expiration = claims.getExpiration();

        long between = Duration.between(Instant.now(), expiration.toInstant()).toMillis(); // RT의 잔여 밀리초
        long twoWeeks = ChronoUnit.WEEKS.getDuration().toMillis() * 2;

        if (between < twoWeeks) {
            redisTemplate.delete(accountId);
            refreshToken = JwtTokenizer.generateRefreshToken(claims.getSubject());
            valueOperations.set(accountId, refreshToken, Duration.ofDays(90L));
        }
        return refreshToken;
    }
}
