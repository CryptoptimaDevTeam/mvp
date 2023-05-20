package CryptOptima.server.domain.coin.service;

import CryptOptima.server.domain.coin.dto.CoinDto;

import java.util.List;

public interface CoinService {
    void createCoin(CoinDto.Create coinDto);
    void updateCoin(CoinDto.Update coinDto, Long coinId);
    List<CoinDto.Response> getCoins(int page, int size);
    CoinDto.Response getCoin(Long coinId);

}
