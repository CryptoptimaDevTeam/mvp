package CryptOptima.server.domain.deposit.service;

import CryptOptima.server.domain.coin.repository.QCoinRepository;
import CryptOptima.server.domain.deposit.dto.DepositDto;
import CryptOptima.server.domain.deposit.dto.DepositMapper;
import CryptOptima.server.domain.deposit.entity.DepositRecord;
import CryptOptima.server.domain.deposit.repository.DepositRepository;
import CryptOptima.server.domain.deposit.repository.QDepositRepository;
import CryptOptima.server.domain.user.QUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService{

    private final DepositMapper depositMapper;
    private final DepositRepository depositRepository;
    private final QDepositRepository qDepositRepository;
    private final QUserRepository userRepository;
    private final QCoinRepository coinRepository;

    // TODO void 형태로 바꾸기
    @Override
    public DepositDto.MngDeposit createDeposit(DepositDto.Create depositDto) {
        DepositRecord deposit = depositMapper.createDepositDtoToDeposit(depositDto);

        deposit.setUser(userRepository.findUserByUserId(depositDto.getUserId()));
        deposit.setCoinPrice(coinRepository.findCoinPriceByCoinId(depositDto.getCoinId()));
        deposit.changeUsdt();

        return depositMapper.depositToDepositMngDto(depositRepository.save(deposit));
    }

    @Override
    public List<DepositDto.UserDeposit> getDepositsByUserId(Long userId, Long exchangeId, Long coinId, String startDate, String endDate) {
        if(coinId==null && exchangeId==null) return qDepositRepository.findUserDepositsByUserIdAndDate(userId);
        else if(coinId==null) return qDepositRepository.findUserDepositsByUserIdAndExchangeIdAndDate(userId,exchangeId);
        else if(exchangeId==null) return qDepositRepository.findUserDepositsByUserIdAndCoinIdAndDate(userId,coinId);
        else return qDepositRepository.findUserDepositsByUserIdAndExchangeIdAndCoinIdAndDate(userId,exchangeId,coinId);
    }

    @Override
    public List<DepositDto.MngDeposit> getDepositsByManger(Long userId, Long exchangeId, Long coinId, String startDate, String endDate) {
        if(userId!=null) {
            if (coinId == null && exchangeId == null) return qDepositRepository.findDepositsByUserIdAndDate(userId);
            else if (coinId == null) return qDepositRepository.findDepositsByUserIdAndExchangeIdAndDate(userId, exchangeId);
            else if (exchangeId == null) return qDepositRepository.findDepositsByUserIdAndCoinIdAndDate(userId, coinId);
            else return qDepositRepository.findDepositsByUserIdAndExchangeIdAndCoinIdAndDate(userId, exchangeId, coinId);
        }
        else {
            if (coinId == null && exchangeId == null) return qDepositRepository.findDepositsByDate();
            else if (coinId == null) return qDepositRepository.findDepositsByExchangeIdAndDate(exchangeId);
            else if (exchangeId == null) return qDepositRepository.findDepositsByCoinIdAndDate(coinId);
            else return qDepositRepository.findDepositsByExchangeIdAndCoinIdAndDate(exchangeId, coinId);
        }
    }
}
