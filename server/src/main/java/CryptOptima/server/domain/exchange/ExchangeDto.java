package CryptOptima.server.domain.exchange;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ExchangeDto {
    @Getter
    static class Create {
        private String exchangeName;
    }
    @Getter
    static class Update {
        private String exchangeName;
    }
    @Setter
    @Builder
    static class Response {
        private Long exchangeId;
        private String exchangeName;
    }

}
