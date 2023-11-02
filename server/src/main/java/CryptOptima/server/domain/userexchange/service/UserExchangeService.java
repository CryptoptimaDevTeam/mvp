package CryptOptima.server.domain.userexchange.service;

import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.entity.UserExchange;

import java.util.List;

public interface UserExchangeService {
    // UID01 :: UID 등록
    void createUserExchange(Long userId, UserExchangeDto.Create userExchangeDto);
    List<UserExchangeDto.ManagerResponse> getUserExchanges(int size, Long userId, Long exchangeId, Long lastUserExchangeId, String uid);
    UserExchangeDto.ManagerResponse getUserExchangeByUserExchangeId(Long userExchangeId);
    List<UserExchangeDto.UserResponse> getUserExchanges(int size, Long userId, Long lastUserExchangeId);
    UserExchangeDto.UserResponse getUserExchangeByUserExchangeId(Long userId, Long userExchangeId);
    void changeUserExchangeStatus(Long userExchangeId, boolean status);
}
