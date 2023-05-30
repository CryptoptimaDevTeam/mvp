package CryptOptima.server.auth.filter;

import CryptOptima.server.auth.jwt.JwtTokenizer;
import CryptOptima.server.auth.managerdetails.ManagerDetails;
import CryptOptima.server.auth.oauth.dto.OAuth2CustomUser;
import CryptOptima.server.auth.utils.AuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
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
    private final AuthorityUtils authorityUtils;

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer, AuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    // 1. OncePerRequestFilter의 메서드를 오버라이드 한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);
        } catch (SignatureException se) { // 서명검증 실패
            request.setAttribute("exception", se);
        } catch (ExpiredJwtException ee) { // 만료된 토큰
            request.setAttribute("exception", ee);
        } catch(Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    // 2. 특정 조건에 대해 true일 때, Filter 동작을 수행하지 않고 다음 필터로 넘긴다.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization"); // JWT 토큰을 가져온다.
        if(authorization == null || !authorization.startsWith("Bearer")){
            return true;
        } // 토큰에 문제가 있으면 true를 리턴한다.
        return false;
    }

    // 3. Signature가 포함된 JWT를 검증(서명을 검증)하고, JWT에 담긴 claims를 가져온다.
    private Map<String,Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws,base64EncodedSecretKey).getBody();

        return claims;
    }

    // 4. 서명 검증이 완료된 JWT에서 accountId를 뽑아 SecurityContext에 저장(Authentication을)한다.
    private void setAuthenticationToContext(Map<String, Object> claims) {

        Authentication authentication;

        // 1. grade를 꺼낸다.
        String grade = (String) claims.get("grade");

        // 2. user라면 userId와 accountId, authorities 를 담은 OAuth2CustomUser 객체를 생성한다.
        if(grade.equals("USER")) {
            Long userId = Long.valueOf((Integer) claims.get("userId")); // JWT claim에서 숫자를 가져올 때 Integer 타입으로 가져온다.
            String accountId = (String) claims.get("accountId");
            List<GrantedAuthority> authorities = authorityUtils.createAuthoritiesByGrade(grade);

            OAuth2CustomUser authUser = OAuth2CustomUser.of(userId, accountId, authorities);
            authentication = new UsernamePasswordAuthenticationToken(authUser, null, authorities);
        }
        // 3. 아니라면 managerId와 accountId, authorities 를 담은 ManagerDetails 객체를 생성한다.
        else {
            Long managerId = Long.valueOf((Integer)claims.get("managerId"));
            String accountId = (String) claims.get("accountId");
            List<GrantedAuthority> authorities = authorityUtils.createAuthoritiesByGrade(grade);

            ManagerDetails authManager = ManagerDetails.of(managerId, accountId, authorities);
            authentication = new UsernamePasswordAuthenticationToken(authManager, null, authorities);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext에 저장
    }
}
