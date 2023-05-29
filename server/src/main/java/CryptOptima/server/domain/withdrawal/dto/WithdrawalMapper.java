package CryptOptima.server.domain.withdrawal.dto;

import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WithdrawalMapper {
    public WithdrawalRecord createWithdrawalDtoToWithdrawal(WithdrawalDto.Create withdrawalDto) {
        return WithdrawalRecord.builder()
                .exchangeId(withdrawalDto.getExchangeId())
                .usdt(withdrawalDto.getReqAmount())
                .build();
    }

    public WithdrawalDto.UserWithdrawal withdrawalToUserWithdrawal(WithdrawalRecord withdrawal) {
        return WithdrawalDto.UserWithdrawal.builder()
                .withdrawalRecordId(withdrawal.getWithdrawalRecordId())
                .exchangeId(withdrawal.getExchangeId())
                .reqAmount(withdrawal.getUsdt())
                .txid(withdrawal.getTxid())
                .withdrawalStatus(withdrawal.getWithdrawalStatus())
                .build();
    }

    public WithdrawalDto.MngWithdrawal withdrawalToMngWithdrawal(WithdrawalRecord withdrawal) {
        return WithdrawalDto.MngWithdrawal.builder()
                .withdrawalRecordId(withdrawal.getWithdrawalRecordId())
                .userId(withdrawal.getUser().getUserId())
                .userName(withdrawal.getUser().getAccountId())
                .exchangeId(withdrawal.getExchangeId())
                .reqAmount(withdrawal.getUsdt())
                .txid(withdrawal.getTxid())
                .withdrawalStatus(withdrawal.getWithdrawalStatus())
                .build();
    }

    public List<WithdrawalDto.UserWithdrawal> withdrawalsToUserWithdrawals(List<WithdrawalRecord> withdrawals) {
        return withdrawals.stream().map(
                withdrawal -> withdrawalToUserWithdrawal(withdrawal)
        ).collect(Collectors.toList());
    }

    public List<WithdrawalDto.MngWithdrawal> withdrawalsToMngWithdrawals(List<WithdrawalRecord> withdrawals) {
        return withdrawals.stream().map(
                withdrawal -> withdrawalToMngWithdrawal(withdrawal)
        ).collect(Collectors.toList());
    }
}
