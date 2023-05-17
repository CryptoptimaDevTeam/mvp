package CryptOptima.server.domain.exchange.dto;


import lombok.*;

public class ExchangeDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String exchangeName;
        private int exchangePossibleUserCount;
        private Float exchangePaybackRate;
        private Float exchangeFeeDiscountRate;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String exchangeName;
        private int exchangePossibleUserCount;
        private Float exchangePaybackRate;
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
