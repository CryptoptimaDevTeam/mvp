package CryptOptima.server.domain.user;

import CryptOptima.server.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    User updateUserDtoToUser(UserDto.Update userDto) {
        return User.builder()
                .status(userDto.getStatus())
                .build();
    }

    UserDto.Response userToUserResponseDto(User user) {
        return UserDto.Response.builder()
                .userId(user.getUserId())
                .sns(user.getRegistrationId())
                .username(user.getUsername())
                .accountId(user.getAccountId())
                .status(user.getStatus())
                .paybackCumAmount(user.getPaybackCumAmount())
                .paybackFinishedAmount(user.getPaybackFinishedAmount())
                .paybackTotalRequestedAmount(user.getPaybackTotalRequestedAmount())
                .build();
    }

    List<UserDto.Response> usersToUserResponseDtos(List<User> users) {
        return users.stream().map(
                user -> userToUserResponseDto(user)
        ).collect(Collectors.toList());
    }
}
