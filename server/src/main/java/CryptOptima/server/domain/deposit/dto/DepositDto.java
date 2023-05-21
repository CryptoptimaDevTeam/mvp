package CryptOptima.server.domain.deposit.dto;

import lombok.Builder;
import lombok.Getter;

public class DepositDto {

    @Getter
    @Builder
    public static class Create {
        private Long exchangeId;
        private Long userId;
        private Long coinId;
        private String depositAmount;
    }

    @Getter
    @Builder
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
}
