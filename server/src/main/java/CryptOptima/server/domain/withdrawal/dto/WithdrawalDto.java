package CryptOptima.server.domain.withdrawal.dto;

import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import lombok.*;

public class WithdrawalDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private Long exchangeId;
        private String reqAmount; // usdt
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateStatus {
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
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
        private WithdrawalRecord.Status withdrawalStatus;
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
        private WithdrawalRecord.Status withdrawalStatus;
        private String createdAt;
    }
}
