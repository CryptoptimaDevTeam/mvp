package CryptOptima.server.domain.userexchange.dto;

import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserExchangeDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @Min(1)
        @NotNull
        private Long exchangeId;
        @NotBlank
        private String uid;
        private String referralCode;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private Long exchangeId;
        private String uid;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class ManagerResponse {
        private Long userExchangeId;
        private Long userId;
        private String userName;
        private Long exchangeId;
        private String exchangeName;
        private String uid;
        private String status;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class UserResponse {
        private Long userExchangeId;
        private Long exchangeId;
        private String exchangeName;
        private String uid;
        private String status;
    }
}
