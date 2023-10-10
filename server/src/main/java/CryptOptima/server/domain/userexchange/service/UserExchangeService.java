package CryptOptima.server.domain.userexchange.service;

import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.entity.UserExchange;

import java.util.List;

public interface UserExchangeService {
    // UID01 :: UID 등록
    void createUserExchange(Long userId, UserExchangeDto.Create userExchangeDto);
    List<UserExchangeDto.Response> getUserExchanges(int size, Long userId, Long exchangeId, Long lastUserExchangeId);
    UserExchangeDto.Response getUserExchangeByUserIdAndExchangeId(Long userId, Long exchangeId);
    void changeUserExchangeStatus(Long userExchangeId, boolean status);
}
