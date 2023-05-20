package CryptOptima.server.domain.coin.dto;

import CryptOptima.server.domain.coin.entity.Coin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoinMapper {

    public Coin createCoinDtoToCoin(CoinDto.Create coinDto) {
        return Coin.builder()
                .coinName(coinDto.getCoinName())
                .price(coinDto.getPrice())
                .build();
    }

    public Coin updateCoinDtoToCoin(CoinDto.Update coinDto) {
        return Coin.builder()
                .coinName(coinDto.getCoinName())
                .price(coinDto.getPrice())
                .build();
    }

    public CoinDto.Response coinToCoinResponseDto(Coin coin) {
        return CoinDto.Response.builder()
                .coinId(coin.getCoinId())
                .coinName(coin.getCoinName())
                .price(coin.getPrice())
                .lastModifiedDate(coin.getLastModifiedAt().toString())
                .build();
    }

    public List<CoinDto.Response> coinsToCoinResponseDtos(List<Coin> coins) {
        return coins.stream().map(
                coin -> coinToCoinResponseDto(coin)
        ).collect(Collectors.toList());
    }
}
