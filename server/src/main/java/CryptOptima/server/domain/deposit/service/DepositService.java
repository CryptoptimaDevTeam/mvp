package CryptOptima.server.domain.deposit.service;

import CryptOptima.server.domain.deposit.dto.DepositDto;

public interface DepositService {
    public DepositDto.MngDeposit createDeposit(DepositDto.Create depositDto);
}
