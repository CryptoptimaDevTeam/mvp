package CryptOptima.server.domain.role.controller;

import CryptOptima.server.domain.role.dto.ResourceDto;
import CryptOptima.server.domain.role.dto.ResourceRoleDto;
import CryptOptima.server.domain.role.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/server/managers/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity createResource(@RequestBody ResourceDto.Create createResourceDto) {
        resourceService.createResource(createResourceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{resource-id}")
    public ResponseEntity getResource(@PathVariable(value = "resource-id") Long resourceId) {

        return new ResponseEntity(resourceService.getResource(resourceId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getResources() { // todo 페이지네이션 도입

        return new ResponseEntity(resourceService.getResources(), HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity createResourceRole(@RequestBody ResourceRoleDto.Create createResourceRoleDto) {
        resourceService.createResourceRole(createResourceRoleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/roles/{resource-role-id}")
    public ResponseEntity deleteResourceRole(@PathVariable("resource-role-id") Long resourceRoleId) {
        resourceService.deleteResourceRole(resourceRoleId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{resource-id}")
    public ResponseEntity deleteResource(@PathVariable("resource-id") Long resourceId) {
        resourceService.deleteResource(resourceId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
