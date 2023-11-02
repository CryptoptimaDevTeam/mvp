package CryptOptima.server.domain.user.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Create {
        @Email
        @NotBlank
        private String accountId;
        private String status;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank
        @Pattern(regexp = "ACTIVE|QUIT|BANNED", message = "Please enter a valid format of status.")
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
        private String referralCode;
        private String status;
        private String paybackCumAmount;
        private String paybackFinishedAmount;
        private String paybackTotalRequestedAmount;
    }
}
