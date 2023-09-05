package CryptOptima.server.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TokenDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Logout {
        @NotBlank
        private String accessToken;
        @NotBlank
        private String refreshToken;
        @Email
        @NotBlank
        private String accountId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Reissue {
        @NotBlank
        private String refreshToken;
        @Email
        @NotBlank
        private String accountId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReissueResponse {
        private String accessToken;
        private String refreshToken;
    }
}
