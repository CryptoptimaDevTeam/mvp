package CryptOptima.server.domain.exchange;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeMapper {

    Exchange createExchangeDtoToExchange(ExchangeDto.Create exchangeDto){
        return Exchange.builder()
                .exchangeName(exchangeDto.getExchangeName())
                .exchangePossibleUserCount(exchangeDto.getExchangePossibleUserCount())
                .exchangePaybackRate(exchangeDto.getExchangePaybackRate())
                .exchangeFeeDiscountRate(exchangeDto.getExchangeFeeDiscountRate())
                .build();
    }

    Exchange updateExchangeDtoToExchange(ExchangeDto.Update exchangeDto) {
        return Exchange.builder()
                .exchangeName(exchangeDto.getExchangeName())
                .exchangePossibleUserCount(exchangeDto.getExchangePossibleUserCount())
                .exchangePaybackRate(exchangeDto.getExchangePaybackRate())
                .exchangeFeeDiscountRate(exchangeDto.getExchangeFeeDiscountRate())
                .build();
    }

    ExchangeDto.Response exchangeToExchangeResponseDto(Exchange exchange) {
        return ExchangeDto.Response.builder()
                .exchangeId(exchange.getExchangeId())
                .exchangeName(exchange.getExchangeName())
                .exchangePossibleUserCount(exchange.getExchangePossibleUserCount())
                .exchangePaybackRate(exchange.getExchangePaybackRate())
                .exchangeFeeDiscountRate(exchange.getExchangeFeeDiscountRate())
                .build();
    }

    List<ExchangeDto.Response> exchangesToExchangeResponseDtos(List<Exchange> exchanges) {
        return exchanges.stream().map(
                exchange -> exchangeToExchangeResponseDto(exchange)
        ).collect(Collectors.toList());
    }
}
