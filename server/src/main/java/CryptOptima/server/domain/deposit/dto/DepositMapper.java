package CryptOptima.server.domain.deposit.dto;

import CryptOptima.server.domain.deposit.entity.DepositRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepositMapper {
    public DepositRecord createDepositDtoToDeposit(DepositDto.Create depositDto) {
        return DepositRecord.builder()
                .exchangeId(depositDto.getExchangeId())
                .coinId(depositDto.getCoinId())
                .depositAmount(depositDto.getDepositAmount())
                .build();
    }

    public DepositDto.MngDeposit depositToDepositMngDto(DepositRecord deposit) {
        return DepositDto.MngDeposit.builder()
                .depositRecordId(deposit.getDepositRecordId())
                .exchangeId(deposit.getExchangeId())
                .userId(deposit.getUser().getUserId())
                .userName(deposit.getUser().getAccountId())
                .coinId(deposit.getCoinId())
                .coinPrice(deposit.getCoinPrice())
                .depositAmount(deposit.getDepositAmount())
                .usdt(deposit.getUsdt())
                .createDate(deposit.getCreatedAt().toString())
                .build();
    }

    public DepositDto.UserDeposit depositToDepositUserDto(DepositRecord deposit) {
        return DepositDto.UserDeposit.builder()
                .depositRecordId(deposit.getDepositRecordId())
                .exchangeId(deposit.getExchangeId())
                .coinId(deposit.getCoinId())
                .coinPrice(deposit.getCoinPrice())
                .depositAmount(deposit.getDepositAmount())
                .usdt(deposit.getUsdt())
                .createDate(deposit.getCreatedAt().toString())
                .build();
    }

    public List<DepositDto.MngDeposit> depositsToDepositMngDtos(List<DepositRecord> deposits) {
        return deposits.stream().map(
                deposit -> depositToDepositMngDto(deposit)
        ).collect(Collectors.toList());
    }

    public List<DepositDto.UserDeposit> depositsToDepositUserDtos(List<DepositRecord> deposits) {
        return deposits.stream().map(
                deposit -> depositToDepositUserDto(deposit)
        ).collect(Collectors.toList());
    }
}
