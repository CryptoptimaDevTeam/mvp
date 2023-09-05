package CryptOptima.server.domain.user.controller;


import CryptOptima.server.domain.user.dto.UserDto;
import CryptOptima.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping("/server")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/users/{user-id}")
    public ResponseEntity updateUser(@PathVariable("user-id") Long userId,
                                     @RequestBody @Valid UserDto.Update userDto) {

        userService.updateUser(userDto, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") Long userId) {

        UserDto.Response response = userService.getUser(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/managers/users")
    public ResponseEntity getUsers(@RequestParam("page") @Min(1) int page, @RequestParam("size") @Min(1) int size) {

        List<UserDto.Response> response = userService.getUsers(page, size);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Min(1) Long userId) {

        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
