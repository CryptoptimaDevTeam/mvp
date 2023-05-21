package CryptOptima.server.domain.deposit.dto;

import CryptOptima.server.domain.deposit.entity.DepositRecord;
import org.springframework.stereotype.Component;

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
}
