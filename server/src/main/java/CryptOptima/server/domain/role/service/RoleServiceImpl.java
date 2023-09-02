package CryptOptima.server.domain.role.service;

import CryptOptima.server.domain.role.dto.RoleDto;
import CryptOptima.server.domain.role.dto.RoleMapper;
import CryptOptima.server.domain.role.entity.Role;
import CryptOptima.server.domain.role.repository.RoleRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void createRole(RoleDto.Create createRoleDto) {
        roleRepository.save(roleMapper.createRoleDtoToRole(createRoleDto));
    }

    @Override
    public void updateRole(Long roleId, RoleDto.Update updateRoleDto) {
        Role findRole = findRoleById(roleId);
        findRole.updateRoleName(updateRoleDto);
    }

    @Override
    public List<RoleDto.Response> getRoles() {
        return roleRepository.findAll().stream().map(
                role -> roleMapper.roleToRoleResponseDto(role)
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.delete(findRoleById(roleId));
    }

    private Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ROLE_NOT_FOUND));
    }
}
