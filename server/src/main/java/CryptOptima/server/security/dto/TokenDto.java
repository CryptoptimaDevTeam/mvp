package CryptOptima.server.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TokenDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Logout {
        private String accessToken;
        private String refreshToken;
        private String accountId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Reissue {
        private String refreshToken;
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
