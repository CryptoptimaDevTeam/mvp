package CryptOptima.server.domain.manager.controller;

import CryptOptima.server.domain.manager.dto.ManagerDto;
import CryptOptima.server.domain.manager.service.ManagerService;
import CryptOptima.server.security.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/server/managers")
public class ManagerController {

    private final ManagerService managerService;

    // MNG_ACCOUNT01 :: 관리자 계정 로그인
    @PostMapping("/login")
    public ResponseEntity loginManager(@RequestBody LoginDto loginDto) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // MNG_ACCOUNT02 :: 관리자 계정 추가
    @PostMapping
    public ResponseEntity createManager(@RequestBody @Valid ManagerDto.Create managerDto) {
        managerService.createManager(managerDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // MNG_ACCOUNT03 :: 관리자 계정 수정
    @PatchMapping("/{manager-id}")
    public ResponseEntity updateManager(@PathVariable("manager-id") Long managerId, @RequestBody ManagerDto.Update managerDto) {
        managerService.updateManager(managerDto, managerId);
        return new ResponseEntity(HttpStatus.OK);
    }

    // MNG_ACCOUNT04 :: 관리자 계정 삭제
    @DeleteMapping("/{manager-id}")
    public ResponseEntity deleteManager(@PathVariable("manager-id") Long managerId) {
        managerService.deleteManager(managerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // MNG_ACCOUNT05 :: 관리자 전체 조회
//    @GetMapping
//    public ResponseEntity getManagers() {
//        return new ResponseEntity(managerService.getManagers(), HttpStatus.OK);
//    }

}