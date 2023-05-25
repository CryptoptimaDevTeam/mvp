package CryptOptima.server.domain.deposit.dto;

import lombok.*;

public class DepositDto {

    @Getter
    @Builder
    public static class Create {
        private Long exchangeId;
        private Long userId;
        private Long coinId;
        private String depositAmount;
    }

    @Data
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MngDeposit {
        private Long depositRecordId;
        private Long exchangeId;
        private Long userId;
        private String userName;
        private Long coinId;
        private String coinPrice;
        private String depositAmount;
        private String usdt;
        private String createDate;
    }

    @Data
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDeposit {
        private Long depositRecordId;
        private Long exchangeId;
        private Long coinId;
        private String coinPrice;
        private String depositAmount;
        public String usdt;
        public String createDate;
    }
}
