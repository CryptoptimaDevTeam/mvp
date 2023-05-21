package CryptOptima.server.domain.deposit.service;

import CryptOptima.server.domain.coin.repository.QCoinRepository;
import CryptOptima.server.domain.deposit.dto.DepositDto;
import CryptOptima.server.domain.deposit.dto.DepositMapper;
import CryptOptima.server.domain.deposit.entity.DepositRecord;
import CryptOptima.server.domain.deposit.repository.DepositRepository;
import CryptOptima.server.domain.user.QUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService{

    private final DepositMapper depositMapper;
    private final DepositRepository depositRepository;
    private final QUserRepository userRepository;
    private final QCoinRepository coinRepository;

    @Override
    public DepositDto.MngDeposit createDeposit(DepositDto.Create depositDto) {
        DepositRecord deposit = depositMapper.createDepositDtoToDeposit(depositDto);

        deposit.setUser(userRepository.findUserByUserId(depositDto.getUserId()));
        deposit.setCoinPrice(coinRepository.findCoinPriceByCoinId(depositDto.getCoinId()));
        deposit.changeUsdt();

        return depositMapper.depositToDepositMngDto(depositRepository.save(deposit));
    }

}
