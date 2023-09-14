package CryptOptima.server.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    private final RedisProperties redisProperties;
    private String host;
    private int port;

    public RedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
        this.host = redisProperties.getHost();
        this.port = redisProperties.getPort();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        System.out.println(this.host + this.port);
        return new LettuceConnectionFactory(this.host, this.port);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}