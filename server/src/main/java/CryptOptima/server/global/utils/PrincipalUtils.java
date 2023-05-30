package CryptOptima.server.global.utils;

import CryptOptima.server.auth.oauth.dto.OAuth2CustomUser;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalUtils {
    public void verifyUserId(Long userId) {
        OAuth2CustomUser principal = (OAuth2CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long principalId = principal.getUserId();
        if(!userId.equals(principalId)) throw new BusinessLogicException(ExceptionCode.INVALID_APPROACH);
    }
}
