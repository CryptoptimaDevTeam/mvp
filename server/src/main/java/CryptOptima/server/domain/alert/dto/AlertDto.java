package CryptOptima.server.domain.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AlertDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class AlertPushDto {
        private String title;
        private String content;
        private LocalDateTime pubDate;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class AlertRequestDto {
        private Long userId;
        private String type;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class AlertResponseDto {
        private Long alertId;
        private String title;
        private String content;
        private boolean status;
        private LocalDateTime pubDate;
    }
}
