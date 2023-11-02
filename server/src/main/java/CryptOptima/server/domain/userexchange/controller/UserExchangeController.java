package CryptOptima.server.domain.userexchange.controller;

import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.service.UserExchangeService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class UserExchangeController {

    private final UserExchangeService userExchangeService;

    // UID01 :: UID 등록
    @PostMapping("/users/{user-id}/registration")
    public ResponseEntity createUserExchange(@PathVariable("user-id") @Min(1) Long userId,
                                             @RequestBody @Valid UserExchangeDto.Create userExchangeDto) {

        userExchangeService.createUserExchange(userId, userExchangeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // UID02 :: UID status 수정
    @PatchMapping("/managers/userexchanges/{userexchange-id}")
    public void changeUserExchangeStatus(@PathVariable("userexchange-id") @Min(1) Long userExchangeId,
                                         @RequestParam(value="status") boolean status) {
        userExchangeService.changeUserExchangeStatus(userExchangeId, status);
    }

    // UID 전체 조회 - manager
    @GetMapping("/managers/userexchanges")
    public ResponseEntity getUserExchanges(@RequestParam(value="size", defaultValue = "10", required = false) @Min(1) int size,
                                           @RequestParam(value = "userId", required = false) @Min(1) Long userId,
                                           @RequestParam(value = "exchangeId", required = false) @Min(1) Long exchangeId,
                                           @RequestParam(value="ltUserExchangeId", defaultValue = "0") @Min(0) Long lastUserExchangeId,
                                           @RequestParam(value = "uid", required = false) String uid) {
        return new ResponseEntity(userExchangeService.getUserExchanges(size, userId, exchangeId, lastUserExchangeId, uid), HttpStatus.OK);
    }

    // UID 조회 - manager
    @GetMapping("/managers/userexchanges/{userexchange-id}")
    public ResponseEntity getUserExchange(@PathVariable("userexchange-id") @Min(1) Long userExchangeId) {
        return new ResponseEntity(userExchangeService.getUserExchangeByUserExchangeId(userExchangeId), HttpStatus.OK);
    }

    // UID 전체 조회 - user
    @GetMapping("/users/{user-id}/userexchanges")
    public ResponseEntity getUserExchanges(@PathVariable("user-id") @Min(1) Long userId,
                                 @RequestParam(value="size", defaultValue = "10", required = false) @Min(1) int size,
                                 @RequestParam(value="ltUserExchangeId", defaultValue = "0") @Min(0) Long lastUserExchangeId) {
        return new ResponseEntity(userExchangeService.getUserExchanges(size, userId, lastUserExchangeId), HttpStatus.OK);
    }

    // UID 조회 - user
    @GetMapping("/users/{user-id}/userexchanges/{userexchange-id}")
    public ResponseEntity getUserExchange(@PathVariable("user-id") @Min(1) Long userId,
                                          @PathVariable("userexchange-id") @Min(1) Long userExchangeId) {
        return new ResponseEntity(userExchangeService.getUserExchangeByUserExchangeId(userId, userExchangeId), HttpStatus.OK);
    }

}
