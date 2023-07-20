package CryptOptima.server.security.provider;

import CryptOptima.server.security.managerdetails.ManagerDetails;
import CryptOptima.server.security.managerdetails.ManagerDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final ManagerDetailsService managerDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationProvider(ManagerDetailsService managerDetailsService, PasswordEncoder passwordEncoder) {
        this.managerDetailsService = managerDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        ManagerDetails managerDetails = (ManagerDetails) managerDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, managerDetails.getManager().getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        } // id 검증 성공, pw 검증 실패

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(managerDetails.getManager(), null, managerDetails.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    } // 파라미터로 전달된 authentication과 UPAT 타입이 일치할 때 CustomAuthenticationProvider가 동작한다.
}
