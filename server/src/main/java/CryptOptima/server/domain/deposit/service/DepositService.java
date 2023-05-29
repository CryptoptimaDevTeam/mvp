package CryptOptima.server.domain.deposit.service;

import CryptOptima.server.domain.deposit.dto.DepositDto;

import java.util.List;

public interface DepositService {
    public DepositDto.MngDeposit createDeposit(DepositDto.Create depositDto);
    public List<DepositDto.UserDeposit> getUserDeposits(
            Long userId, Long exchangeId, Long coinId, String startDate, String endDate);

    public List<DepositDto.MngDeposit> getMngDeposits(
            Long userId, Long exchangeId, Long coinId, String startDate, String endDate);

    // TODO 필터링 기능을 구현할 때 optional한 필터는 어떻게 처리하는지
    // Service 단에서 인자에 따라 Repository 메서드들 여러개 구현하여 매핑하는지
}