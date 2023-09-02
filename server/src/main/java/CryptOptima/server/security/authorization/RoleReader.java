package CryptOptima.server.security.authorization;

import CryptOptima.server.domain.role.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleReader {

    private final ResourceRepository resourceRepository;

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap = new LinkedHashMap<>();

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceMap() {
        return this.resourceMap;
    }

    // 동적 권한 변경을 반영한다.
    @Transactional // todo 왜 @Transactional 애너테이션을 붙이면 LazyInitializationException이 발생하지 않는 것인가?
    public void updateResourceMap() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap = new LinkedHashMap<>();

        resourceRepository.findAll().forEach(resource -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            resource.getResourceRoleList().forEach(resourceRole -> {
                configAttributes.add(new SecurityConfig(resourceRole.getRole().getRoleName()));
            });
            resourceMap.put(new AntPathRequestMatcher(resource.getResourceUrl()), configAttributes);
        });

        this.resourceMap = resourceMap;
    }
}