package CryptOptima.server.domain.userexchange.service;

import CryptOptima.server.domain.exchange.entity.Exchange;
import CryptOptima.server.domain.exchange.repository.ExchangeRepository;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.domain.userexchange.dto.UserExchangeDto;
import CryptOptima.server.domain.userexchange.dto.UserExchangeMapper;
import CryptOptima.server.domain.userexchange.entity.UserExchange;
import CryptOptima.server.domain.userexchange.repository.QUserExchangeRepository;
import CryptOptima.server.domain.userexchange.repository.UserExchangeRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserExchangeServiceImpl implements UserExchangeService {

    private final UserRepository userRepository;
    private final ExchangeRepository exchangeRepository;
    private final UserExchangeMapper userExchangeMapper;
    private final UserExchangeRepository userExchangeRepository;
    private final QUserExchangeRepository qUserExchangeRepository;
    private final MailService mailService;

    @Override
    @Transactional
    public void createUserExchange(Long userId, UserExchangeDto.Create userExchangeDto) {
        User findUser = userRepository.findById(userId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        Exchange findExchange = exchangeRepository.findById(userExchangeDto.getExchangeId()).orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.EXCHANGE_NOT_FOUND));
        Long referralUserId = userExchangeDto.getReferralCode() == null ?
                0L : userRepository.findByReferralCode(userExchangeDto.getReferralCode()).orElseThrow(
                        () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        ).getUserId();

        UserExchange userExchange = userExchangeMapper.createUserExchangeDtoToUserExchange(userExchangeDto);
        userExchange.setUser(findUser);
        userExchange.setExchange(findExchange);
        userExchange.setReferralUserId(referralUserId);

        userExchangeRepository.save(userExchange);
    }

    @Override
    public List<UserExchangeDto.Response> getUserExchanges(int size, Long lastUserExchangeId) {
        List<UserExchange> userExchanges = qUserExchangeRepository.findAllUserExchanges(size, lastUserExchangeId);
        return userExchangeMapper.userExchangesToUserExchangeDtos(userExchanges);
    }

    // 사용자 id로 조회
    @Override
    public List<UserExchangeDto.Response> getUserExchangesByUserId(int size, Long userId, Long lastUserExchangeId) {
        List<UserExchange> userExchanges = qUserExchangeRepository.findUserExchangesByUserId(size, userId, lastUserExchangeId);
        return userExchangeMapper.userExchangesToUserExchangeDtos(userExchanges);
    }

    // 거래소 id로 조회
    @Override
    public List<UserExchangeDto.Response> getUserExchangesByExchangeId(int size, Long exchangeId, Long lastUserExchangeId) {
        List<UserExchange> userExchanges = qUserExchangeRepository.findUserExchangesByExchangeId(size, exchangeId, lastUserExchangeId);
        return userExchangeMapper.userExchangesToUserExchangeDtos(userExchanges);
    }

    // 사용자 id 및 거래소 id로 조회
    public UserExchangeDto.Response getUserExchangeByUserIdAndExchangeId(Long userId, Long exchangeId) {
        UserExchange userExchange = qUserExchangeRepository.findUserExchangeByUserIdAndExchangeId(userId, exchangeId);
        return userExchangeMapper.userExchangeToUserExchangeResponseDto(userExchange);
    public void changeUserExchangeStatus(Long userExchangeId, boolean status) {
        UserExchange userExchange = userExchangeRepository.findById(userExchangeId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_EXCHANGE_NOT_FOUND)
        );

        if(status) {
            userExchange.changeStatus(UserExchange.Status.CONFIRMED);
        }
        else userExchange.changeStatus(UserExchange.Status.FAILED);

        String subject = (status) ? "[Cryptoptima] Your UID registration confirmed!" : "[Cryptoptima] Your UID registration failed.";
        mailService.sendUidConfirmationMail(userExchange, subject);
    }
}
