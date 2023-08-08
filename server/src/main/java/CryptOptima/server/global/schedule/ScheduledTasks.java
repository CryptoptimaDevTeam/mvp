package CryptOptima.server.global.schedule;

import CryptOptima.server.domain.coin.entity.Coin;
import CryptOptima.server.domain.coin.repository.CoinRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    RestTemplate restTemplate = new RestTemplate();
    private final CoinRepository coinRepository;

    private long getUnixTime() {
        LocalDate currentDate = LocalDate.now();
        LocalTime midnight = LocalTime.of(0, 0);
        LocalDateTime dateTime = LocalDateTime.of(currentDate,midnight);
        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }

    private String getPrice(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            return jsonNode.get("BTC").get("USD").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron="00 00 09 * * * ")
    public void task() {
        // 1. coin 엔티티 리스트를 가져온다.
        List<Coin> coins = coinRepository.findAll();
        long ts = getUnixTime();

        // 2. coinName 별로 API를 호출해 ts 기준 종가 USDT를 얻는다.
        for(Coin c: coins) {
            String apiUri = "https://min-api.cryptocompare.com/data/pricehistorical?fsym=" +
                    c.getCoinName() + "&tsyms=USD,KRW&ts=" + ts;

            // 2-1) 외부 API에 요청을 보낸다.
            ResponseEntity<String> response = restTemplate.getForEntity(apiUri, String.class);

            // 2-2) USD 환산가를 갱신한다.
            String price = getPrice(response.getBody());
            c.setPrice(price);
        }
    }
}
