package CryptOptima.server.domain.userexchange.controller;

import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.service.UserExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

@Validated
@Controller
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

    // Todo if-else문 개선
    // UID02 :: UID 조회
    @GetMapping("/public/userexchanges")
    public ResponseEntity getUserExchanges(@RequestParam("size") @Min(1) int size,
                                           @RequestParam(value = "userId", required = false, defaultValue = "0") @Min(1) Long userId,
                                           @RequestParam(value = "exchangeId", required = false, defaultValue = "0") @Min(1) Long exchangeId,
                                           @RequestParam("ltUserExchangeId") @Min(1) Long lastUserExchangeId) {
        List<UserExchangeDto.Response> response;
        if(userId==0 && exchangeId==0) response = userExchangeService.getUserExchanges(size, lastUserExchangeId);
        else if(userId!=0 && exchangeId==0) response = userExchangeService.getUserExchangesByUserId(size, userId, lastUserExchangeId);
        else if(userId==0 && exchangeId!=0) response = userExchangeService.getUserExchangesByExchangeId(size, exchangeId, lastUserExchangeId);
        else {
            return new ResponseEntity(userExchangeService.getUserExchangeByUserIdAndExchangeId(userId, exchangeId),HttpStatus.OK);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
