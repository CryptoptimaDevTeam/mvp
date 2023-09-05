package CryptOptima.server.domain.exchange.controller;

import CryptOptima.server.domain.exchange.service.ExchangeService;
import CryptOptima.server.domain.exchange.dto.ExchangeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server")
public class ExchangeController {

    private final ExchangeService exchangeService;

    // 1. 거래소 등록
    @PostMapping("/managers/exchanges")
    ResponseEntity createExchange(@RequestBody @Valid ExchangeDto.Create exchangeDto) {
        ExchangeDto.Response response = exchangeService.createEcxhange(exchangeDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    // 2. 거래소 정보 수정
    @PatchMapping("/managers/exchanges/{exchange-id}")
    ResponseEntity updateExchange(@PathVariable("exchange-id") @Min(1) Long exchangeId,
                                  @RequestBody @Valid ExchangeDto.Update exchangeDto) {
        ExchangeDto.Response response = exchangeService.updateExchange(exchangeDto,exchangeId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // 3. 거래소 조회
    @GetMapping("/public/exchanges/{exchange-id}")
    ResponseEntity getExchange(@PathVariable("exchange-id") @Min(1) Long exchangeId) {
        ExchangeDto.Response response = exchangeService.getExchangeById(exchangeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/public/exchanges")
    ResponseEntity getExchanges(@RequestParam(value = "page", defaultValue = "1") @Min(1) int page,
                                @RequestParam(value ="size", defaultValue = "20") @Min(1) int size) {
        List<ExchangeDto.Response> response = exchangeService.getExchanges(page-1, size);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // 4. 거래소 삭제
    @DeleteMapping("/managers/exchanges/{exchange-id}")
    ResponseEntity deleteExchanges(@PathVariable("exchange-id") @Min(1) Long exchangeId) {
        exchangeService.deleteExchange(exchangeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
