package CryptOptima.server.global.utils;

import CryptOptima.server.global.exception.BusinessLogicException;
import CryptOptima.server.global.exception.ExceptionCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

@Component
public class IpUtils {

    @Value("${env.ip.token}")
    String token;

    WebClient client = WebClient.builder()
            .baseUrl("http://ipinfo.io/")
            .build();

    public String getIp(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if(ipAddress == null) throw new BusinessLogicException(ExceptionCode.HEADER_IS_MISSING);

        return ipAddress;
    }

    public String getNationByIp(String ip) {
        return parseJsonToNation(sendAndReadJson(ip));
    }

    private JsonNode sendAndReadJson(String ip) {

        String uri = ip + "?token=" + token;

        String response = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readTree(response);
        } catch (JsonMappingException e) {
            throw new BusinessLogicException(ExceptionCode.JSON_MAPPING_ERROR);
        } catch (JsonProcessingException e) {
            throw new BusinessLogicException(ExceptionCode.JSON_MAPPING_ERROR);
        }
    }

    private String parseJsonToNation(JsonNode jsonNode) {
        return jsonNode.get("country").asText();
    }
}
