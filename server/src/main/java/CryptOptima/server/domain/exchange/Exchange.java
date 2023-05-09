package CryptOptima.server.domain.exchange;

import javax.persistence.*;

@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangeId;

    @Column(nullable = false)
    private String exchangeName;
}
