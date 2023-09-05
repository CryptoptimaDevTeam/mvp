package CryptOptima.server.domain.userexchange.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserExchangeDto {
    @Getter
    @Builder
    public static class Create {
        @Min(1)
        private Long exchangeId;
        @NotBlank
        private String uid;
    }

    @Getter
    @Builder
    public static class Update {
        private Long exchangeId;
        private String uid;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long userExchangeId;
        private Long userId;
        private String userName;
        private Long exchangeId;
        private String exchangeName;
        private String uid;
    }
}
