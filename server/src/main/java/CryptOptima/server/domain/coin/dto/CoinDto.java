package CryptOptima.server.domain.coin.dto;

import lombok.*;

public class CoinDto {

    @Getter
    @Builder
    public static class Create {
        private String coinName;
        private String price;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String coinName;
        private String price;
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
