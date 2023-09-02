package CryptOptima.server.domain.role.controller;

import CryptOptima.server.domain.role.dto.RoleDto;
import CryptOptima.server.domain.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/server/admin/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity createRole(@RequestBody RoleDto.Create createRoleDto) {
        roleService.createRole(createRoleDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{role-id}")
    public ResponseEntity updateRole(@PathVariable("role-id") Long roleId,
                                     RoleDto.Update updateRoleDto) {
        roleService.updateRole(roleId, updateRoleDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getRoles() {
        return new ResponseEntity(roleService.getRoles(), HttpStatus.OK);
    }

    @DeleteMapping("/{role-id}")
    public ResponseEntity deleteRole(@PathVariable("role-id") Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
