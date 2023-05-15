package CryptOptima.server.domain.exchange;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeMapper {

    Exchange createExchangeDtoToExchange(ExchangeDto.Create exchangeDto){
        return Exchange.builder()
                .exchangeName(exchangeDto.getExchangeName())
                .build();
    }

    Exchange updateExchangeDtoToExchange(ExchangeDto.Update exchangeDto) {
        return Exchange.builder()
                .exchangeName(exchangeDto.getExchangeName())
                .build();
    }

    ExchangeDto.Response exchangeToExchangeResponseDto(Exchange exchange) {
        return ExchangeDto.Response.builder()
                .exchangeId(exchange.getExchangeId())
                .exchangeName(exchange.getExchangeName())
                .build();
    }

    List<ExchangeDto.Response> exchangesToExchangeResponseDtos(List<Exchange> exchanges) {
        return exchanges.stream().map(
                exchange -> exchangeToExchangeResponseDto(exchange)
        ).collect(Collectors.toList());
    }
}
