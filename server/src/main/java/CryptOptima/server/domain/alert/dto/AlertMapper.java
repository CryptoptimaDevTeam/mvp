package CryptOptima.server.domain.alert.dto;

import CryptOptima.server.domain.alert.entity.Alert;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AlertMapper {
    public AlertDto.AlertPushDto alertToAlertPushDto(Alert alert) {
        return AlertDto.AlertPushDto.builder()
                .title(alert.getAlertTitle())
                .content(alert.getAlertContent())
                .pubDate(alert.getCreatedAt())
                .build();
    }

    public AlertDto.AlertPushDto alertEventToAlertPushDto(String title, String content) {
        return AlertDto.AlertPushDto.builder()
                .title(title)
                .content(content)
                .pubDate(LocalDateTime.now())
                .build();
    }
}
