package CryptOptima.server.domain.deposit.controller;

import CryptOptima.server.domain.deposit.dto.DepositDto;
import CryptOptima.server.domain.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/server/managers/deposits")
    public ResponseEntity getDeposits(@RequestParam(required = false, value = "userId") Long userId,
                                      @RequestParam(required = false, value = "exchangeId") Long exchangeId,
                                      @RequestParam(required = false, value = "coinId") Long coinId,
                                      @RequestParam(required = false, value = "startDate") String startDate,
                                      @RequestParam(required = false, value = "endDate") String endDate) {

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     *  MNG_DEPOSIT03 :: 페이백 입금 등록
     */
    @PostMapping("/server/managers/deposits")
    public ResponseEntity createDeposits(@RequestBody DepositDto.Create depositDto) {
        DepositDto.MngDeposit response = depositService.createDeposit(depositDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
