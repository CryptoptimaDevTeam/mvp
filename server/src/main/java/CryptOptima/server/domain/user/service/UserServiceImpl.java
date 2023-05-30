package CryptOptima.server.domain.user.service;

import CryptOptima.server.domain.user.dto.UserDto;
import CryptOptima.server.domain.user.dto.UserMapper;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CustomBeanUtils<User> beanUtils;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto.Update userDto, Long userId) {
        User user = userMapper.updateUserDtoToUser(userDto);
        User updatingUser = beanUtils.copyNonNullProperties(user,findUserById(userId));
        userRepository.save(updatingUser);
    }

    @Override
    public UserDto.Response getUser(Long userId) {
        User findUser = findUserById(userId);
        return userMapper.userToUserResponseDto(findUser);
    }

    @Override
    public List<UserDto.Response> getUsers(int page, int size) {
        List<User> users = userRepository.findAll(
                PageRequest.of(page,size, Sort.by("userId").descending())
        ).getContent();
        return userMapper.usersToUserResponseDtos(users);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(findUserById(userId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
