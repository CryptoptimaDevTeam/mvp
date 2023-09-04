package CryptOptima.server.security.filter;

import CryptOptima.server.domain.manager.entity.Manager;
import CryptOptima.server.domain.manager.repository.ManagerRepository;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ErrorResponse;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.utils.ErrorResponder;
import CryptOptima.server.security.jwt.JwtService;
import CryptOptima.server.security.jwt.JwtTokenizer;
import CryptOptima.server.security.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO AT 만료 경우, RT 를 조회
        // TODO RT 만료시간이 2주 이내로 남은경우 재발급.

        try {

            String accessToken = request.getHeader("Authorization").substring(6);
            jwtService.access(accessToken);

            Map<String, Object> claims = JwtTokenizer.parseClaims(accessToken).getBody();

            Authentication upat = createSuccessfulAuthentication(claims);

            // SecurityContext에 UPAT 저장
            SecurityContextHolder.getContext().setAuthentication(upat);

            filterChain.doFilter(request, response); // 반드시 다음 필터로 요청을 넘길 것

        } catch(ExpiredJwtException ej) {
            ErrorResponder.sendErrorResponse(response, ErrorResponse.of(ExceptionCode.ACCESS_TOKEN_EXPIRED));
        } catch (Exception e) {
            ErrorResponder.sendErrorResponse(response,ErrorResponse.of(ExceptionCode.ACCESS_TOKEN_NOT_VALID));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String url = request.getRequestURI();
        List<String> urls = List.of(
                "/server/public",
                "/server/managers/login",
                "/oauth2/authorization"
        );

        for(String s : urls) {
            if(url.contains(s)) return true;
        }
        return false;
    }

    private Authentication createSuccessfulAuthentication(Map<String, Object> claims) {
        // user인지 manager인지 구분하기 위한 역할 파싱
        String role = (String) claims.get("role");

        // 역할에 따른 권한정보 생성
        List<GrantedAuthority> authorities = CustomAuthorityUtils.createAuthoritiesByRole(role);

        switch(role) { // 역할에 따른 Principal 생성
            case "MANAGER":
                Manager findManager = managerRepository.findById(Long.parseLong(String.valueOf(claims.get("managerId"))))
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

                UsernamePasswordAuthenticationToken managerAuthentication =
                        new UsernamePasswordAuthenticationToken(findManager,null, authorities);

                return managerAuthentication;

            default:
                User findUser = userRepository.findById(Long.parseLong(String.valueOf(claims.get("userId"))))
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

                UsernamePasswordAuthenticationToken userAuthentication =
                        new UsernamePasswordAuthenticationToken(findUser,null, authorities);

                return userAuthentication;
        }
    }
}
