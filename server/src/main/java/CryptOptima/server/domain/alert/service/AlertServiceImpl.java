package CryptOptima.server.domain.alert.service;

import CryptOptima.server.domain.alert.entity.Alert;
import CryptOptima.server.domain.alert.repository.AlertRepository;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.user.repository.UserRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService{

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    // 1. 알림 RUD
    public Alert createAlert(Long userId, String title, String content) {

        Alert alert = Alert.builder()
                .user(findUserById(userId))
                .alertTitle(title)
                .alertContent(content)
                .alertStatus(false)
                .build();

        return alertRepository.save(alert);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

}
