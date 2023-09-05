package CryptOptima.server.domain.deposit.controller;

import CryptOptima.server.domain.deposit.dto.DepositDto;
import CryptOptima.server.domain.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    /**
     *  MNG_DEPOSIT01 :: 페이백 입금 조회
     *  TODO 기능 요구사항 명확히 하기
     */

    /**
     *  MNG_DEPOSIT02 :: 페이백 입금 목록 필터링 조회
     *  userId, exchangeId, startDate & endDate, coinId
     *  TODO startDate, endDate defaultValue 정하기
     */

    // Todo 날짜 유효성 검증
    @GetMapping("/server/users/{user-id}/deposits")
    public ResponseEntity getUserDeposits(@PathVariable("user-id") Long userId,
                                      @RequestParam(required = false, value = "exchangeId") @Min(1) Long exchangeId,
                                      @RequestParam(required = false, value = "coinId") @Min(1) Long coinId,
                                      @RequestParam(required = false, value = "startDate", defaultValue = "") String startDate,
                                      @RequestParam(required = false, value = "endDate", defaultValue = "") String endDate) {
        List<DepositDto.UserDeposit> response= depositService.getUserDeposits(userId, exchangeId, coinId, startDate, endDate);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/server/managers/deposits")
    public ResponseEntity getMngDeposits(@RequestParam(required = false, value = "userId") @Min(1) Long userId,
                                      @RequestParam(required = false, value = "exchangeId") @Min(1) Long exchangeId,
                                      @RequestParam(required = false, value = "coinId") @Min(1) Long coinId,
                                      @RequestParam(required = false, value = "startDate", defaultValue = "") String startDate,
                                      @RequestParam(required = false, value = "endDate", defaultValue = "") String endDate) {
        List<DepositDto.MngDeposit> response= depositService.getMngDeposits(userId, exchangeId, coinId, startDate, endDate);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    /**
     *  MNG_DEPOSIT03 :: 페이백 입금 등록
     */
    @PostMapping("/server/managers/deposits")
    public ResponseEntity createDeposit(@RequestBody @Valid DepositDto.Create depositDto) {
        DepositDto.MngDeposit response = depositService.createDeposit(depositDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
