package CryptOptima.server.domain.role.service;

import CryptOptima.server.domain.role.dto.ResourceDto;
import CryptOptima.server.domain.role.dto.ResourceMapper;
import CryptOptima.server.domain.role.dto.ResourceRoleDto;
import CryptOptima.server.domain.role.entity.Resource;
import CryptOptima.server.domain.role.entity.ResourceRole;
import CryptOptima.server.domain.role.entity.Role;
import CryptOptima.server.domain.role.repository.ResourceRepository;
import CryptOptima.server.domain.role.repository.ResourceRoleRepository;
import CryptOptima.server.domain.role.repository.RoleRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.security.authorization.RoleReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRoleRepository resourceRoleRepository;
    private final ResourceRepository resourceRepository;
    private final RoleRepository roleRepository;
    private final ResourceMapper resourceMapper;
    private final RoleReader roleReader;

    // 1. Resource 생성 & ResourceRole 생성
    @Override
    public void createResource(ResourceDto.Create dto) {
        // 1) Resource를 생성한다.
        Resource resource = resourceMapper.createResourceDtoToResource(dto);

        // 2) roleIds를 기반으로 한 List<Role>을 resource에 저장한다.
        resource.updateResourceRoleList(
                resourceMapper.createResourceDtoToResourceList(dto, resource));
        resourceRepository.save(resource);

        roleReader.updateResourceMap();
    }

    // 2. Resource 조회
    @Override
    public ResourceDto.Response getResource(Long resourceId) {
        return resourceMapper.resourceToResourceResponseDto(findResourceById(resourceId));
    }

    @Override
    public List<ResourceDto.Response> getResources() {
        return resourceRepository.findAll().stream().map(
                resource -> resourceMapper.resourceToResourceResponseDto(resource)
        ).collect(Collectors.toList());
    }

    // 3. Resource 수정 - ResourceRole 생성, 삭제
    public void createResourceRole(ResourceRoleDto.Create dto) {
        ResourceRole resourceRole = ResourceRole.builder()
                .resource(findResourceById(dto.getResourceId()))
                .role(findRoleById(dto.getRoleId()))
                .build();

        resourceRoleRepository.save(resourceRole);
        roleReader.updateResourceMap();
    }

    public void deleteResourceRole(Long resourceRoleId) {
        resourceRoleRepository.deleteById(resourceRoleId);
        roleReader.updateResourceMap();
    }
    // TODO ResourceRole 개별 추가, 삭제 대신 Resource update 하나로 수정

    private Resource findResourceById(Long resourceId) {
        return resourceRepository.findById(resourceId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.RESOURCE_NOT_FOUND)
        );
    }

    private Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ROLE_NOT_FOUND)
        );
    }

    // 4. Resource 삭제
    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
        roleReader.updateResourceMap();
    }

}
