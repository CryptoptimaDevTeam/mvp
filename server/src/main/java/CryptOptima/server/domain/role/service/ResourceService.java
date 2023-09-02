package CryptOptima.server.domain.role.service;

import CryptOptima.server.domain.role.dto.ResourceDto;
import CryptOptima.server.domain.role.dto.ResourceRoleDto;

import java.util.List;

public interface ResourceService {

    void createResource(ResourceDto.Create createResourceDto);
    List<ResourceDto.Response> getResources();
    ResourceDto.Response getResource(Long resourceId);
    void createResourceRole(ResourceRoleDto.Create createResourceRoleDto);
    void deleteResourceRole(Long resourceRoleId);
    void deleteResource(Long resourceId);
}
