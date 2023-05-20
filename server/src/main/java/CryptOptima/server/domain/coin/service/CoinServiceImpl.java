package CryptOptima.server.domain.coin.service;

import CryptOptima.server.domain.coin.dto.CoinDto;
import CryptOptima.server.domain.coin.dto.CoinMapper;
import CryptOptima.server.domain.coin.entity.Coin;
import CryptOptima.server.domain.coin.repository.CoinRepository;
import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService{

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final CustomBeanUtils<Coin> beanUtils; // TODO Coin 엔티티 change 메서드 사용 수정

    // MNG_UPDATE01 :: 주요 코인의 시세를 업데이트 한다.
    @Override
    public void updateCoin(CoinDto.Update coinDto, Long coinId) {
        Coin coin = coinMapper.updateCoinDtoToCoin(coinDto);
        Coin findCoin = findById(coinId);

        coinRepository.save(beanUtils.copyNonNullProperties(coin, findCoin));
    }

    // MNG_UPDATE02 :: 주요 코인들의 시세를 조회한다.
    @Override
    public List<CoinDto.Response> getCoins(int page, int size) {
        List<Coin> coins = coinRepository.findAll(
                PageRequest.of(page, size, Sort.by("coinId").descending())
        ).getContent();
        return coinMapper.coinsToCoinResponseDtos(coins);
    }

    // MNG_UPDATE03 :: 신규 코인을 등록한다.
    @Override
    public void createCoin(CoinDto.Create coinDto) {
        Coin coin = coinMapper.createCoinDtoToCoin(coinDto);
        coinRepository.save(coin);
    }

    @Override
    public CoinDto.Response getCoin(Long coinId) {
        Coin findCoin = findById(coinId);
        return coinMapper.coinToCoinResponseDto(findCoin);
    }

    private Coin findById(Long coinId) {
        return coinRepository.findById(coinId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COIN_NOT_FOUND)
        );
    }
}
