package CryptOptima.server.domain.coin.controller;

import CryptOptima.server.domain.coin.dto.CoinDto;
import CryptOptima.server.domain.coin.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/server")
public class CoinController {

    private final CoinService coinService;

    // MNG_UPDATE01 :: 주요 코인들의 시세를 업데이트 한다.
    // TODO RequestBody type 정하기. { String: value }Map or String
    @PatchMapping("/managers/crypto-price")
    public ResponseEntity updateCoins(@RequestBody Map<String, String> coinValues) {
        return new ResponseEntity(HttpStatus.OK);
    }

    // MNG_UPDATE01 :: 주요 코인의 시세를 업데이트 한다.
    @PatchMapping("/managers/crypto-price/{coin-id}")
    public ResponseEntity updateCoin(@PathVariable("coin-id") Long coinId,
                                     @RequestBody CoinDto.Update coinDto) {
        coinService.updateCoin(coinDto, coinId);
        return new ResponseEntity(HttpStatus.OK);
    }

    // MNG_UPDATE02 :: 주요 코인들의 시세를 조회한다.
    @GetMapping("/public/crypto-price")
    public ResponseEntity getCoins(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "20") int size) {
        List<CoinDto.Response> response = coinService.getCoins(page-1, size);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // MNG_UPDATE03 :: 신규 코인을 등록한다.
    @PostMapping("/managers/crypto-price")
    public ResponseEntity createCoin(@RequestBody CoinDto.Create coinDto) {
        coinService.createCoin(coinDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
