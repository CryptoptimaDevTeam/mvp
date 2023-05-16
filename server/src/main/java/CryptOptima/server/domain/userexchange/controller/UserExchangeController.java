package CryptOptima.server.domain.userexchange.controller;

import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.service.UserExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/server")
@RequiredArgsConstructor
public class UserExchangeController {

    private final UserExchangeService userExchangeService;

    // UID01 :: UID 등록
    @PostMapping("/users/{user-id}/registration")
    public ResponseEntity createUserExchange(@PathVariable("user-id") Long userId,
                                             @RequestBody UserExchangeDto.Create userExchangeDto) {

        userExchangeService.createUserExchange(userId, userExchangeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // UID02 :: UID 조회
    @GetMapping("/managers/userexchanges")
    public ResponseEntity getUserExchanges(@RequestParam("size") int size,
                                           @RequestParam(value = "userId", required = false, defaultValue = "0") Long userId,
                                           @RequestParam(value = "exchangeId", required = false, defaultValue = "0") Long exchangeId,
                                           @RequestParam("ltUserExchangeId") Long lastUserExchangeId) {
        List<UserExchangeDto.Response> response;
        if(userId==0 && exchangeId==0) response = userExchangeService.getUserExchanges(size, lastUserExchangeId);
        else if(userId==0 && exchangeId!=0) response = userExchangeService.getUserExchangesByExchangeId(size, exchangeId, lastUserExchangeId);
        else response = userExchangeService.getUserExchangesByUserId(size, userId, lastUserExchangeId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
