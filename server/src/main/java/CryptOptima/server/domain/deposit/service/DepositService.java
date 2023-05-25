package CryptOptima.server.domain.deposit.service;

import CryptOptima.server.domain.deposit.dto.DepositDto;

import java.util.List;

public interface DepositService {
    public DepositDto.MngDeposit createDeposit(DepositDto.Create depositDto);
    public List<DepositDto.UserDeposit> getDepositsByUserId(Long userId, Long exchangeId, Long coinId,
                                                            String startDate, String endDate);

    public List<DepositDto.MngDeposit> getDepositsByManger(Long userId, Long exchangeId, Long coinId,
                                                           String startDate, String endDate);
    // 두 메서드는 동일한 Repo 메서드를 공유할 수 있겠다. Deposit 객체를 뽑아와서 해당 DTO로 매핑하여
    // 컨트롤러에 넘겨주면 되겠다.

    // TODO 필터링 기능을 구현할 때 optional한 필터는 어떻게 처리하는지
    // Service 단에서 인자에 따라 Repository 메서드들 여러개 구현하여 매핑하는지
}