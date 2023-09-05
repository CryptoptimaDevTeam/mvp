package CryptOptima.server.domain.exchange.dto;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ExchangeDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String exchangeName;
        @Min(0)
        @NotBlank
        private int exchangePossibleUserCount;
        @Min(0)
        @NotBlank
        private Float exchangePaybackRate;
        @Min(0)
        @NotBlank
        private Float exchangeFeeDiscountRate;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String exchangeName;
        @Min(0)
        private int exchangePossibleUserCount;
        @Min(0)
        private Float exchangePaybackRate;
        @Min(0)
        private Float exchangeFeeDiscountRate;
    }
    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long exchangeId;
        private String exchangeName;
        private int exchangePossibleUserCount;
        private Float exchangePaybackRate;
        private Float exchangeFeeDiscountRate;
    }
}
