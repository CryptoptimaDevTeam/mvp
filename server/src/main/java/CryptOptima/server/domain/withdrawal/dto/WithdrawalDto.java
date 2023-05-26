package CryptOptima.server.domain.withdrawal.dto;

import lombok.*;

public class WithdrawalDto {

    @Getter
    @Builder
    public static class Create {
        private Long exchangeId;
        private String reqAmount; // usdt
    }

    @Getter
    @Builder
    public static class UpdateStatus {
        private String status;
    }

    @Getter
    @Builder
    public static class UpdateTxid {
        private String txid;
    }

    @Data
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserWithdrawal {
        private Long withdrawalRecordId;
        private Long exchangeId;
        private String reqAmount; // USDT
        private String txid;
        private String withdrawalStatus;
        private String createdAt;
    }

    @Data
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MngWithdrawal {
        private Long withdrawalRecordId;
        private Long userId;
        private String userName; // accountId
        private Long exchangeId;
        private String reqAmount; // USDT
        private String txid;
        private String withdrawalStatus;
        private String createdAt;
    }
}
