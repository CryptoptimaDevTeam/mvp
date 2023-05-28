package CryptOptima.server.domain.withdrawal.service;

import CryptOptima.server.domain.user.QUserRepository;
import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;
import CryptOptima.server.domain.withdrawal.dto.WithdrawalMapper;
import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import CryptOptima.server.domain.withdrawal.repository.QWithdrawalRepository;
import CryptOptima.server.domain.withdrawal.repository.WithdrawalRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService{

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
        withdrawalRepository.save(withdrawal);
    }

    @Override
    public void updateWithdrawalStatus(String withdrawalStatus, Long withdrawalId) {
        WithdrawalRecord withdrawal = findWithdrawalByWithdrawalId(withdrawalId);
        withdrawal.changeWithdrawalStatus(WithdrawalRecord.Status.valueOf(withdrawalStatus)); // dirty-check
    }

    @Override
    public void updateWithdrawalTxid(String txid, Long withdrawalId) {
        WithdrawalRecord withdrawal = findWithdrawalByWithdrawalId(withdrawalId);
        withdrawal.changeTxid(txid); // dirty-check
    }

    // TODO SortType & ASC/DESC 추가하기
    public List<WithdrawalDto.UserWithdrawal> getUserWithdrawals(Long userId, Long exchangeId/*, String startDate, String endDate*/) {
        if(exchangeId==null) return qWithdrawalRepository.findUserWithdrawalsByUserIdAndDate(userId/*, startDate, endDate*/);
        else return qWithdrawalRepository.findUserWithdrawalsByUserIdAndExchangeIdAndDate(userId,exchangeId/*,startDate, endDate*/);
    }

    public List<WithdrawalDto.MngWithdrawal> getMngWithdrawals(Long userId, Long exchangeId/*, String startDate, String endDate*/) {
        if(userId==null) {
            if(exchangeId==null) return qWithdrawalRepository.findMngWithdrawalsByDate(/*startDate, endDate*/);
            else return qWithdrawalRepository.findMngWithdrawalsByExchangeIdAndDate(exchangeId/*, startDate, endDate*/);
        }
        else {
            if(exchangeId==null) return qWithdrawalRepository.findMngWithdrawalsByUserIdAndDate(userId/*, startDate, endDate*/);
            else return qWithdrawalRepository.findMngWithdrawalsByUserIdAndExchangeIdAndDate(userId,exchangeId/*, startDate, endDate*/);
        }
    }

    private WithdrawalRecord findWithdrawalByWithdrawalId(Long withdrawalId) {
        return withdrawalRepository.findById(withdrawalId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WITHDRAWAL_NOT_FOUND));
    }
}
