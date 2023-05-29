package CryptOptima.server.domain.withdrawal.controller;

import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;
import CryptOptima.server.domain.withdrawal.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/server")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @PostMapping("/users/{user-id}/withdrawals")
    public ResponseEntity createWithdrawal(@PathVariable("user-id") Long userId,
                                           @RequestBody WithdrawalDto.Create withdrawalDto) {
        withdrawalService.createWithdrawal(withdrawalDto, userId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/users/{user-id}/withdrawals/{withdrawal-id}/cancel")
    public ResponseEntity changeWithdrawalStatus(@PathVariable("user-id")Long userId,
                                                 @PathVariable("withdrawal-id") Long withdrawalId){
        withdrawalService.updateWithdrawalStatus("CANCELED", withdrawalId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/managers/withdrawals/{withdrawal-id}/status")
    public ResponseEntity changeWithdrawalStatus(@PathVariable("withdrawal-id") Long withdrawalId,
                                                 @RequestBody WithdrawalDto.UpdateStatus withdrawalDto){
        withdrawalService.updateWithdrawalStatus(withdrawalDto.getStatus(), withdrawalId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/managers/withdrawals/{withdrawal-id}/txid")
    public ResponseEntity changeWithdrawalTxid(@PathVariable("withdrawal-id") Long withdrawalId,
                                               @RequestBody WithdrawalDto.UpdateTxid withdrawalDto) {
        withdrawalService.updateWithdrawalTxid(withdrawalDto.getTxid(), withdrawalId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users/{user-id}/withdrawals")
    public ResponseEntity getUserWithdrawals(@PathVariable("user-id") Long userId,
                                             @RequestParam(required = false, value = "exchangeId") Long exchangeId,
                                             @RequestParam(required = false, value = "status") String status,
                                             @RequestParam(required = false, value = "startDate", defaultValue = "") String startDate,
                                             @RequestParam(required = false, value = "endDate", defaultValue = "") String endDate) {

        List<WithdrawalDto.UserWithdrawal> response = withdrawalService.getUserWithdrawals(userId,exchangeId,status/*, startDate, endDate*/);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // TODO sort asc/desc
    @GetMapping("/managers/withdrawals")
    public ResponseEntity getMngWithdrawals(@RequestParam(required = false, value = "userId") Long userId,
                                            @RequestParam(required = false, value = "exchangeId") Long exchangeId,
                                            @RequestParam(required = false, value = "status") String status,
                                            @RequestParam(required = false, value = "startDate", defaultValue = "") String startDate,
                                            @RequestParam(required = false, value = "endDate", defaultValue = "") String endDate) {

        List<WithdrawalDto.MngWithdrawal> response = withdrawalService.getMngWithdrawals(userId, exchangeId, status/*, startDate, endDate*/);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
