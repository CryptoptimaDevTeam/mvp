package CryptOptima.server.domain.exchange;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
@Getter
@Setter
@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangeId;

    @Column(nullable = false, unique = true)
    private String exchangeName;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer exchangePossibleUserCount; // 등록 가능한 유저 수

    @Column(nullable = false)
    @ColumnDefault("0")
    private Float exchangePaybackRate; // 페이백율

    @Column(nullable = false)
    @ColumnDefault("0")
    private Float exchangeFeeDiscountRate; // 수수료 할인율

//  private int exchangePaybackAmount;
//  각 거래소 마다 유저들의 평균치
}