package CryptOptima.server.domain.deposit.service;

import CryptOptima.server.domain.coin.repository.QCoinRepository;
import CryptOptima.server.domain.deposit.dto.DepositDto;
import CryptOptima.server.domain.deposit.dto.DepositMapper;
import CryptOptima.server.domain.deposit.entity.DepositRecord;
import CryptOptima.server.domain.deposit.repository.DepositRepository;
import CryptOptima.server.domain.deposit.repository.QDepositRepository;
import CryptOptima.server.domain.user.repository.QUserRepository;

import CryptOptima.server.global.event.PushAlertEvent;
import CryptOptima.server.global.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService{

    private final ApplicationEventPublisher eventPublisher;
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
        deposit.changeUsdt();   // 입금액을 USDT로 환산한다.
        deposit.plusPaybackCumAmount(); // User의 총 누적입금액을 증액한다.

        DepositRecord savedDepositRecord = depositRepository.save(deposit);

        eventPublisher.publishEvent(new PushAlertEvent(savedDepositRecord.getUser().getUserId(),
                PushAlertEvent.Type.DEPOSIT,savedDepositRecord));

        return depositMapper.depositToDepositMngDto(savedDepositRecord);
    }

    @Override
    public List<DepositDto.UserDeposit> getUserDeposits(Long userId, Long exchangeId, Long coinId, String startDate, String endDate, Integer size, Long ltDepositId) {
        return qDepositRepository.findUserDeposits(userId, exchangeId, coinId,
                DateTimeUtils.parseLocalDateTime(startDate, "start"), DateTimeUtils.parseLocalDateTime(endDate, "end"), size, ltDepositId);
    }

    @Override
    public List<DepositDto.MngDeposit> getMngDeposits(Long userId, Long exchangeId, Long coinId, String startDate, String endDate, Integer size, Long ltDepositId) {
        return qDepositRepository.findMngDeposits(userId, exchangeId, coinId,
                DateTimeUtils.parseLocalDateTime(startDate, "start"), DateTimeUtils.parseLocalDateTime(endDate, "end"), size, ltDepositId);
    }
}
