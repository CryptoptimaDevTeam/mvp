package CryptOptima.server.domain.coin.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coinId;

    @Column(unique = true, nullable = false)
    private String coinName;

    @Column(nullable = false)
    private String price;

    private void changeCoinName(String coinName) {
        this.coinName = coinName;
    }

    private void changePrice(String price) {
        this.price = price;
    }
}
