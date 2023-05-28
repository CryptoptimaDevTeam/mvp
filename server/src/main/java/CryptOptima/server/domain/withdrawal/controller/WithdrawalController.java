package CryptOptima.server.domain.withdrawal.controller;

import CryptOptima.server.domain.withdrawal.dto.WithdrawalDto;
import CryptOptima.server.domain.withdrawal.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // TODO GetMapping 추가
}
