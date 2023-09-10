package CryptOptima.server.domain.userexchange.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import CryptOptima.server.domain.exchange.entity.Exchange;
import CryptOptima.server.domain.user.entity.User;
import lombok.*;

import jakarta.persistence.*;

//TODO createdAt 칼럼만 남기기
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExchange extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userExchangeId;

    // #Case1: 탈퇴한 유저들의 등록 목록을 조회할 때 user.userStatus 으로 접근 가능
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    // 입금 관리 페이지에서 사용됨 -> uid를 통해 userId를 알아냄, userId에 입금액을 USDT로 환산해 depositRecord를 DB에 저장함.
    @Column(unique = true, nullable = false)
    private String uid;
}
