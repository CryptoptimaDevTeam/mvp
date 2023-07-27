package CryptOptima.server.security.jwt;

import CryptOptima.server.security.dto.TokenDto;
import org.springframework.data.redis.connection.RedisConnectionFactory;

public interface JwtService {

    // 1. 로그인 시 RT 저장
    void login(String accountId, String refreshToken);

    // 2. 접근 AT 조회
    void access(String accessToken);

    // 3. 재발급 RT 조회, RT 삭제, RT 등록
    TokenDto.ReissueResponse reissue(String accountId, String refreshToken);

    // 4. 로그아웃 AT 등록, RT 삭제
    void logout(String accountId, String accessToken, String refreshToken);
}