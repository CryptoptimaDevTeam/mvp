package CryptOptima.server.domain.alert;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;

/**
 * Alert
 * 알림 엔티티
 */

@Entity
public class Alert extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(nullable = false)
    private Long userId; // TODO 탈퇴한 회원id로 alert를 삭제한다.

    @Column(nullable = false)
    private String alertContent;

    // TODO 읽지않은/읽은 알림 조회 시, userId와 status=false select 쿼리 vs. user left join userAlert
    // TODO 알림삭제 시, alertId로 삭제 vs. alertId로 삭제 + userAlert(안읽음) 삭제
    @Column(nullable = false)
    private boolean alertStatus;

    // TODO (일반적으로 0,1으로 표기하는지 or Enum으로 표기하는지 알아보기)
    // 알림을 읽은 상태로 변경한다
    public void readAlertStatus() {
        this.alertStatus = true;
    }

    public void unreadAlertStatus() {
        this.alertStatus = false;
    }

    // 알림을 삭제한다
    // 읽은 알림, 읽지 않은 알림으로 정렬한다.
}