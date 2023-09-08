package CryptOptima.server.domain.withdrawal.service;

import CryptOptima.server.domain.user.repository.QUserRepository;
import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;
import CryptOptima.server.domain.withdrawal.dto.WithdrawalMapper;
import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import CryptOptima.server.domain.withdrawal.repository.QWithdrawalRepository;
import CryptOptima.server.domain.withdrawal.repository.WithdrawalRepository;
import CryptOptima.server.global.event.PushAlertEvent;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService{

    private final ApplicationEventPublisher eventPublisher;
    private final WithdrawalMapper withdrawalMapper;
    private final WithdrawalRepository withdrawalRepository;
    private final QWithdrawalRepository qWithdrawalRepository;
    private final QUserRepository qUserRepository;

    @Override
    @Transactional
    public void createWithdrawal(WithdrawalDto.Create withdrawalDto, Long userId) {
        WithdrawalRecord withdrawal = withdrawalMapper.createWithdrawalDtoToWithdrawal(withdrawalDto);

        withdrawal.setUser(qUserRepository.findUserByUserId(userId));
        withdrawal.isWithdrawalPossible();
        withdrawal.plusPaybackTotalReqAmount();

        WithdrawalRecord savedWithdrawalRecord = withdrawalRepository.save(withdrawal);
        eventPublisher.publishEvent(new PushAlertEvent(userId, PushAlertEvent.Type.WITHDRAWAL, savedWithdrawalRecord));

    }

    @Override
    public void updateWithdrawalStatus(String withdrawalStatus, Long withdrawalId) {
        WithdrawalRecord withdrawal = findWithdrawalByWithdrawalId(withdrawalId);
        withdrawal.changeWithdrawalStatus(WithdrawalRecord.Status.valueOf(withdrawalStatus));

        // todo 이벤트리스너 퍼블리싱
    }

    @Override
    public void updateWithdrawalTxid(String txid, Long withdrawalId) {
        WithdrawalRecord withdrawal = findWithdrawalByWithdrawalId(withdrawalId);
        withdrawal.changeTxid(txid);

        // todo 이벤트리스너 퍼블리싱
    }

    public List<WithdrawalDto.UserWithdrawal> getUserWithdrawals(Long userId, Long exchangeId, String status, String startDate, String endDate, int size, Long ltWithdrawalId) {
        return qWithdrawalRepository.findUserWithdrawals(userId, exchangeId, status,
                DateTimeUtils.parseLocalDateTime(startDate, "start"), DateTimeUtils.parseLocalDateTime(endDate, "end"), size, ltWithdrawalId);
    }

    // userId, exchangeId, status, date를 기준으로 withdrawal을 조회한다.
    public List<WithdrawalDto.MngWithdrawal> getMngWithdrawals(Long userId, Long exchangeId, String status, String startDate, String endDate, int size, Long ltWithdrawalId) {
        return qWithdrawalRepository.findMngWithdrawals(userId, exchangeId, status,
                DateTimeUtils.parseLocalDateTime(startDate, "start"), DateTimeUtils.parseLocalDateTime(endDate, "end"), size, ltWithdrawalId);
    }

    private WithdrawalRecord findWithdrawalByWithdrawalId(Long withdrawalId) {
        return withdrawalRepository.findById(withdrawalId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WITHDRAWAL_NOT_FOUND));
    }

}
