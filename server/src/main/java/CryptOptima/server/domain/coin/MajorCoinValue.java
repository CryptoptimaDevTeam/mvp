package CryptOptima.server.domain.coin;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class MajorCoinValue extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorCoinValueId;

    @Column(unique = true, nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String price;
}
