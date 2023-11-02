package CryptOptima.server.domain.userexchange.dto;

import CryptOptima.server.domain.userexchange.entity.UserExchange;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserExchangeMapper {
    public UserExchange createUserExchangeDtoToUserExchange(UserExchangeDto.Create userExchangeDto) {
        return UserExchange.builder()
                .uid(userExchangeDto.getUid())
                .status(UserExchange.Status.PENDING)
                .build();
    }

    public UserExchangeDto.ManagerResponse userExchangeToManagerResponseDto(UserExchange userExchange) {
        return UserExchangeDto.ManagerResponse.builder()
                .userExchangeId(userExchange.getUserExchangeId())
                .userId(userExchange.getUser().getUserId())
                .userName(userExchange.getUser().getAccountId())
                .exchangeId(userExchange.getExchange().getExchangeId())
                .exchangeName(userExchange.getExchange().getExchangeName())
                .uid(userExchange.getUid())
                .status(userExchange.getStatus().toString())
                .build();
    }

    public List<UserExchangeDto.ManagerResponse> userExchangesToManagerResponseDtos(List<UserExchange> userExchanges) {
        return userExchanges.stream().map(
                userExchange -> userExchangeToManagerResponseDto(userExchange)
        ).collect(Collectors.toList());
    }

    public UserExchangeDto.UserResponse userExchangeToUserResponseDto(UserExchange userExchange) {
        return UserExchangeDto.UserResponse.builder()
                .userExchangeId(userExchange.getUserExchangeId())
                .exchangeId(userExchange.getExchange().getExchangeId())
                .exchangeName(userExchange.getExchange().getExchangeName())
                .uid(userExchange.getUid())
                .status(userExchange.getStatus().toString())
                .build();
    }

    public List<UserExchangeDto.UserResponse> userExchangesToUserResponseDtos(List<UserExchange> userExchanges) {
        return userExchanges.stream().map(
                userExchange -> userExchangeToUserResponseDto(userExchange)
        ).collect(Collectors.toList());
    }
}
