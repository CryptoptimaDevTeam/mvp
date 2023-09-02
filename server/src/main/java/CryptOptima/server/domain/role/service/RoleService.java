package CryptOptima.server.domain.role.service;

import CryptOptima.server.domain.role.dto.RoleDto;

import java.util.List;

public interface RoleService {
    void createRole(RoleDto.Create createRoleDto);
    void updateRole(Long roleId, RoleDto.Update updateRoleDto);
    List<RoleDto.Response> getRoles();
    void deleteRole(Long roleId);
}
