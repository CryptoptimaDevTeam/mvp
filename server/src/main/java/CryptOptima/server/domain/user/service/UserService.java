package CryptOptima.server.domain.user.service;

import CryptOptima.server.domain.user.dto.UserDto;
import CryptOptima.server.domain.user.entity.User;

import java.util.List;

public interface UserService {
    // MEM_LOGIN :: SNS 로그인
    void createUser(User user);
    void updateUser(UserDto.Update userDto, Long userId);
    UserDto.Response getUser(Long userId);
    List<UserDto.Response> getUsers(int page, int size);
    void deleteUser(Long userId);

}
