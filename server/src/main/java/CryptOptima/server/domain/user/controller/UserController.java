package CryptOptima.server.domain.user.controller;

import CryptOptima.server.domain.user.dto.UserDto;
import CryptOptima.server.domain.user.service.UserService;
import CryptOptima.server.global.utils.PrincipalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/server/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PrincipalUtils principalUtils;

    @PatchMapping("/{user-id}")
    public ResponseEntity updateUser(@PathVariable("user-id") Long userId,
                                     @RequestBody UserDto.Update userDto) {

//        principalUtils.verifyUserId(String.valueOf(userId));
        userService.updateUser(userDto, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") Long userId) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        UserDto.Response response = userService.getUser(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<UserDto.Response> response = userService.getUsers(page, size);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
