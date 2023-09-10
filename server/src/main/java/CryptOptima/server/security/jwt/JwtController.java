package CryptOptima.server.security.jwt;

import CryptOptima.server.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    // 매니저 로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody @Valid TokenDto.Logout tokenDto) {
        // 0. 필터체인에 의해 AT는 검증 받은 상태, todo 로그아웃 토큰을 body로 주고 받는 것이 옳은가
        // 1. JwtService호출
        jwtService.logout(tokenDto.getAccountId(), tokenDto.getAccessToken(), tokenDto.getRefreshToken());
        return ResponseEntity.status(200).body("로그아웃 됨 ㅊㅋ");
    }

    @PostMapping("/reissue")
    public ResponseEntity reissueToken(@RequestBody @Valid TokenDto.Reissue tokenDto) {
        TokenDto.ReissueResponse response = jwtService.reissue(tokenDto.getAccountId(), tokenDto.getRefreshToken());
        return ResponseEntity.status(201).body(response);
    }
}
