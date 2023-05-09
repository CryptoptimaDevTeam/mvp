package CryptOptima.server.domain.user;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;

/**
 * UserExchange
 * 사용자가 당사의 referral code로 등록한 거래소 엔티티
 */

//TODO createdAt 칼럼만 남기기
@Entity
public class UserExchange extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userExchangeId;

    // #Case1: 탈퇴한 유저들의 등록 목록을 조회할 때 user.userStatus 으로 접근 가능
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    @Column(nullable = false)
    private Long exchangeId;

    // 입금 관리 페이지에서 사용됨 -> uid를 통해 userId를 알아냄, userId에 입금액을 USDT로 환산해 depositRecord를 DB에 저장함.
    @Column(unique = true, nullable = false)
    private String uid;
}
