package CryptOptima.server.domain.exchange;


import lombok.*;

public class ExchangeDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Create {
        private String exchangeName;
        private int exchangePossibleUserCount;
        private Float exchangePaybackRate;
        private Float exchangeFeeDiscountRate;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Update {
        private String exchangeName;
        private int exchangePossibleUserCount;
        private Float exchangePaybackRate;
        private Float exchangeFeeDiscountRate;
    }
    @Getter
    @Setter
    @Builder
    static class Response {
        private Long exchangeId;
        private String exchangeName;
        private int exchangePossibleUserCount;
        private Float exchangePaybackRate;
        private Float exchangeFeeDiscountRate;
    }
}
