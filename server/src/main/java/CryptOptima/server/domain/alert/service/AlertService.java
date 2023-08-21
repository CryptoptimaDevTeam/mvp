package CryptOptima.server.domain.alert.service;

import CryptOptima.server.domain.alert.dto.AlertDto;
import CryptOptima.server.domain.alert.entity.Alert;

import java.util.List;

public interface AlertService {

    // 1. 알림 RUD

    // 2. 알림 Create
    public Alert createAlert(Long userId, String title, String content);
}
