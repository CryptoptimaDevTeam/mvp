package CryptOptima.server.domain.user.controller;


import CryptOptima.server.domain.user.dto.UserDto;
import CryptOptima.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/server")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/users/{user-id}")
    public ResponseEntity updateUser(@PathVariable("user-id") Long userId,
                                     @RequestBody UserDto.Update userDto) {

        userService.updateUser(userDto, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") Long userId) {

        UserDto.Response response = userService.getUser(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/managers/users")
    public ResponseEntity getUsers(@RequestParam("page") int page, @RequestParam("size") int size) {

        List<UserDto.Response> response = userService.getUsers(page, size);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") Long userId) {

        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @PostMapping("/users/logout")
//    public ResponseEntity logoutUser(HttpServletRequest request) {
//        String accessToken = request.getHeader("Authorization").substring(6);
//        String refreshToken = request.getHeader("Refresh");
////        redisService.logout(accessToken,refreshToken);
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    @PostMapping("/users/token")
//    public ResponseEntity reissueToken(@RequestBody TokenDto.Request tokenDto) {
//        // todo 토큰 DTO -> 액세스만 주거나 리프레시도 주거나.
//        redisService.isRefreshExists(tokenDto.getAccountId(), tokenDto.getRefreshToken());
//        String newAccess = redisService.reissueAccessToken(
//                userService.findUserByAccountId(tokenDto.getAccountId()), tokenDto.getRefreshToken()
//        );
//        return new ResponseEntity(TokenDto.Response.builder().accessToken(newAccess).build(), HttpStatus.CREATED);
//    }

//    @PostMapping("/users/logout")

}
