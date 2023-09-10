package CryptOptima.server.domain.coin.dto;

import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CoinDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @Min(0)
        @NotBlank
        private String price;
        @NotBlank
        private String coinName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @Min(0)
        private String price;
        private String coinName;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long coinId;
        private String coinName;
        private String price;
        private String lastModifiedDate;
    }
}
