package CryptOptima.server.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final RoleReader roleReader;

    // 요청에 해당하는 권한 목록을 반환한다.
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest curRequest = ((FilterInvocation) object).getRequest();

        for(Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : roleReader.getResourceMap().entrySet()) {
            RequestMatcher matcher = entry.getKey();
            if(matcher.matches(curRequest)) {
                return entry.getValue();
            }
        }

        return null;
    }

    // 모든 요청의 권한 목록을 반환한다.
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> result = new HashSet<>();

        for(Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : roleReader.getResourceMap().entrySet()) {
            List<ConfigAttribute> list = entry.getValue();
            if(list != null) result.addAll(list);
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void initResourceMap() {
        this.roleReader.updateResourceMap();
    }
}
