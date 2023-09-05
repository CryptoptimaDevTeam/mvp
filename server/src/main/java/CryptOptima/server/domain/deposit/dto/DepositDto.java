package CryptOptima.server.domain.deposit.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class DepositDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @Min(1)
        @NotBlank
        private Long exchangeId;
        @Min(1)
        @NotBlank
        private Long userId;
        @Min(1)
        @NotBlank
        private Long coinId;
        @Min(0)
        @NotBlank
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
