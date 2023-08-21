package CryptOptima.server.domain.alert.entity;

import CryptOptima.server.domain.BaseTimeEntity;
import CryptOptima.server.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alert extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String alertTitle;

    @Column(nullable = false)
    private String alertContent;

    // TODO 읽지않은/읽은 알림 조회 시, userId와 status=false select 쿼리 vs. user left join userAlert
    // TODO 알림삭제 시, alertId로 삭제 vs. alertId로 삭제 + userAlert(안읽음) 삭제
    @Column(nullable = false)
    private boolean alertStatus;

    // 알림을 읽은 상태로 변경한다
    public void readAlertStatus() {
        this.alertStatus = true;
    }

    public void unreadAlertStatus() {
        this.alertStatus = false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 알림을 삭제한다
    // 읽은 알림, 읽지 않은 알림으로 정렬한다.
}