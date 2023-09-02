package CryptOptima.server.domain.role.service;

import CryptOptima.server.domain.role.dto.ResourceDto;
import CryptOptima.server.domain.role.dto.ResourceMapper;
import CryptOptima.server.domain.role.entity.Resource;
import CryptOptima.server.domain.role.entity.Role;
import CryptOptima.server.domain.role.repository.ResourceRepository;
import CryptOptima.server.domain.role.repository.RoleRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    // 1. Resource 생성 & ResourceRole 생성
    @Override
    public void createResource(ResourceDto.Create dto) {
        // 1) Resource를 생성한다.
        Resource resource = resourceMapper.createResourceDtoToResource(dto);

        // 2) roleIds를 기반으로한 List<Role>을 resource에도 저장한다.
        resource.updateResourceRoleList(
                resourceMapper.createResourceDtoToResourceList(dto, resource));
        resourceRepository.save(resource);
    }

    // 4. Resource 조회
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

    private Resource findResourceById(Long resourceId) {
        return resourceRepository.findById(resourceId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.RESOURCE_NOT_FOUND)
        );
    }

}
