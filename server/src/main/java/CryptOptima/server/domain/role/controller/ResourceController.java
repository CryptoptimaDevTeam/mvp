package CryptOptima.server.domain.role.controller;

import CryptOptima.server.domain.role.dto.ResourceDto;
import CryptOptima.server.domain.role.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/server/admin/resources")
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
}
