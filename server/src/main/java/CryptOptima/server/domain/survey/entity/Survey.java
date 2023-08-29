package CryptOptima.server.domain.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String exchange;

    @Column(nullable = false)
    private String type;

}
