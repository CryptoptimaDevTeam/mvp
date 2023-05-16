package CryptOptima.server.domain.exchange;

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
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final ExchangeMapper exchangeMapper;
    private final CustomBeanUtils<Exchange> beanUtils;

    @Override
    public ExchangeDto.Response createEcxhange(ExchangeDto.Create exchangeDto) {
        Exchange exchange = exchangeMapper.createExchangeDtoToExchange(exchangeDto);
        exchangeRepository.save(exchange);
        return exchangeMapper.exchangeToExchangeResponseDto(exchange);
    }

    @Override
    public ExchangeDto.Response updateExchange(ExchangeDto.Update exchangeDto, Long exchangeId) {
        Exchange exchange = exchangeMapper.updateExchangeDtoToExchange(exchangeDto);
        Exchange updatingExchange = beanUtils.copyNonNullProperties(exchange, findExchangeById(exchangeId));
        exchangeRepository.save(updatingExchange);
        return exchangeMapper.exchangeToExchangeResponseDto(updatingExchange);
    }

    public ExchangeDto.Response getExchangeById(Long exchangeId) {
        Exchange findExchange = findExchangeById(exchangeId);
        return exchangeMapper.exchangeToExchangeResponseDto(findExchange);
    }

    @Override
    public void deleteExchange(Long exchangeId) {
        Exchange findExchange = findExchangeById(exchangeId);
        exchangeRepository.delete(findExchange);
    }

    @Override // TODO QueryDSL 적용
    public List<ExchangeDto.Response> getExchanges(int page, int size) {
        List<Exchange> exchanges = exchangeRepository.findAll(
                PageRequest.of(page,size, Sort.by("exchangeId").descending())
        ).getContent();
        return exchangeMapper.exchangesToExchangeResponseDtos(exchanges);
    }

    private Exchange findExchangeById(Long exchangeId) {
        return exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.EXCHANGE_NOT_FOUND));
    }
}
