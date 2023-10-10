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

    public UserExchangeDto.Response userExchangeToUserExchangeResponseDto(UserExchange userExchange) {
        return UserExchangeDto.Response.builder()
                .userExchangeId(userExchange.getUserExchangeId())
                .userId(userExchange.getUser().getUserId())
                .userName(userExchange.getUser().getAccountId())
                .exchangeId(userExchange.getExchange().getExchangeId())
                .exchangeName(userExchange.getExchange().getExchangeName())
                .uid(userExchange.getUid())
                .build();
    }

    public List<UserExchangeDto.Response> userExchangesToUserExchangeDtos(List<UserExchange> userExchanges) {
        return userExchanges.stream().map(
                userExchange -> userExchangeToUserExchangeResponseDto(userExchange)
        ).collect(Collectors.toList());
    }
}
