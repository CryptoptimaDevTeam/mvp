package CryptOptima.server.domain.role.dto;

import CryptOptima.server.domain.role.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto.Response roleToRoleResponseDto(Role role) {
        return RoleDto.Response.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
    }

    public Role createRoleDtoToRole(RoleDto.Create createRoleDto) {
        return Role.builder()
                .roleName(createRoleDto.getRoleName())
                .build();
    }
}
