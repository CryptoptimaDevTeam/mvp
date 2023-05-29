package CryptOptima.server.domain.withdrawal.service;

import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;

import java.util.List;

public interface WithdrawalService {
    public void createWithdrawal(WithdrawalDto.Create withdrawalDto, Long userId);
    public void updateWithdrawalStatus(String withdrawalStatus, Long withdrawalId);
    public void updateWithdrawalTxid(String txid, Long withdrawalId);
    public List<WithdrawalDto.UserWithdrawal> getUserWithdrawals(Long userId, Long exchangeId, String status/*, String startDate, String endDate*/);
    public List<WithdrawalDto.MngWithdrawal> getMngWithdrawals(Long userId, Long exchangeId, String status/*, String startDate, String endDate*/);
}
