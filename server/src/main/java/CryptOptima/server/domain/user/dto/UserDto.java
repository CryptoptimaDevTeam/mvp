package CryptOptima.server.domain.user.dto;

import lombok.*;

public class UserDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Create {
        private String accountId;
        private String status;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String status;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private long userId;
        private String accountId;
        private String sns;
        private String username;
        private String status;
        private String paybackCumAmount;
        private String paybackFinishedAmount;
        private String paybackTotalRequestedAmount;
    }
}
