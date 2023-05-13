package CryptOptima.server.security.filter;

import CryptOptima.server.security.jwt.JwtTokenizer;
import CryptOptima.server.security.utils.ManagerAuthorityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final ManagerAuthorityUtils authorityUtils;

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer, ManagerAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    // 1. OncePerRequestFilter의 메서드를 오버라이드 한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> claims = verifyJws(request);
        setAuthenticationToContext(claims);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization"); // JWT 토큰을 가져온다.
        return authorization == null || !authorization.startsWith("Bearer"); // 토큰에 문제가 있으면 true를 리턴한다.
    }

    // 2. Signature가 포함된 JWT를 검증(서명을 검증)하고, JWT에 담긴 claims를 가져온다.
    private Map<String,Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws,base64EncodedSecretKey).getBody();

        return claims;
    }

    // 3. 서명 검증이 완료된 JWT에서 accountId를 뽑아 SecurityContext에 저장(Authentication을)한다.
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String accountId = (String) claims.get("accountId"); // Principal 인 accountId 를 얻어옴.
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List) claims.get("roles")); // authorities를 얻어옴.
        Authentication authentication = new UsernamePasswordAuthenticationToken(accountId, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext에 저장
    }
}
