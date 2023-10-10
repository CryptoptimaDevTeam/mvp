package CryptOptima.server.domain.userexchange.controller;

import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.service.UserExchangeService;
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

    // UID02 :: UID 조회
    @GetMapping("/public/userexchanges")
    public ResponseEntity getUserExchanges(@RequestParam("size") @Min(1) int size,
                                           @RequestParam(value = "userId", required = false) @Min(1) Long userId, // todo 없으면 null?
                                           @RequestParam(value = "exchangeId", required = false) @Min(1) Long exchangeId,
                                           @RequestParam("ltUserExchangeId") @Min(1) Long lastUserExchangeId) {
        List<UserExchangeDto.Response> response;
        response = userExchangeService.getUserExchanges(size, userId, exchangeId, lastUserExchangeId);
        if (userId != null && exchangeId != null) response.add(userExchangeService.getUserExchangeByUserIdAndExchangeId(userId, exchangeId));

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/public/userexchanges/{userexchange-id}")
    public void changeUserExchangeStatus(@PathVariable("userexchange-id") Long userExchangeId,
                                         @RequestParam(value="status") boolean status) {
        userExchangeService.changeUserExchangeStatus(userExchangeId, status);
    }
}
