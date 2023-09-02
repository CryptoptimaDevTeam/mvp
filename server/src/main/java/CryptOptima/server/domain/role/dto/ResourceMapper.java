package CryptOptima.server.domain.role.dto;

import CryptOptima.server.domain.role.entity.Resource;
import CryptOptima.server.domain.role.entity.ResourceRole;
import CryptOptima.server.domain.role.entity.Role;
import CryptOptima.server.domain.role.repository.RoleRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResourceMapper {

    private final RoleRepository roleRepository;

    // ResourceCreateDto를 Resource로 변환한다.
    public Resource createResourceDtoToResource(ResourceDto.Create dto) {
        return Resource.builder()
                .resourceUrl(dto.getUrl())
                .order(dto.getOrder())
                .build();
    }

    // ResourceCreateDto를 기반으로 Resource에 저장될 ResourceRole 리스트를 반환한다.
    public List<ResourceRole> createResourceDtoToResourceList(ResourceDto.Create dto, Resource resource) {
        List<ResourceRole> resourceRoleList = new ArrayList<>();

        for(Long roleId : dto.getRoleIds()) {
            Role findRole = roleRepository.findById(roleId).orElseThrow(
                    () -> new BusinessLogicException(ExceptionCode.ROLE_NOT_FOUND)
            );

            resourceRoleList.add(ResourceRole.builder()
                    .resource(resource)
                    .role(findRole)
                    .build());
        }

        return resourceRoleList;
    }

    // Resource를 ResourceResponseDto로 변환한다.
    public ResourceDto.Response resourceToResourceResponseDto(Resource resource) {
        return ResourceDto.Response.builder()
                .resourceId(resource.getResourceId())
                .resourceUrl(resource.getResourceUrl())
                .order(resource.getOrder())
                .resourceRoleList(resource.getResourceRoleList().stream().map(
                        resourceRole -> resourceRoleToResourceRoleResponseDto(resourceRole)
                        ).collect(Collectors.toList()))
                .build();
    }

    // ResourceRole을 ResourceRoleResponseDto로 변환한다.
    private ResourceRoleDto.Response resourceRoleToResourceRoleResponseDto(ResourceRole resourceRole) {
        return ResourceRoleDto.Response.builder()
                .resourceRoleId(resourceRole.getResourceRoleId())
                .roleName(resourceRole.getRole().getRoleName())
                .build();
    }

}
