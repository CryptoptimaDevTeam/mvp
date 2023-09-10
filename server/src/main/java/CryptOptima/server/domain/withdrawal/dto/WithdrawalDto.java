package CryptOptima.server.domain.withdrawal.dto;

import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class WithdrawalDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @Min(1)
        @NotNull
        private Long exchangeId;
        @NotBlank
        private String reqAmount; // usdt
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateStatus {
        @Pattern(regexp = "REQUESTED|PENDING|COMPLETE|FAILED|CANCELED",
                message = "Please enter a valid format of status.")
        @NotBlank
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateTxid {
        @NotBlank
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
