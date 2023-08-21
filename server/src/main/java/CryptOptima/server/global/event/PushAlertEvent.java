package CryptOptima.server.global.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PushAlertEvent {
    private Long userId;
    private Type type;
    private AlertEvent eventEntity; // DepositRecord, WithdrawalRecord 등 Alert 엔티티를 저장에 필요한 인스턴스 데이터

    public enum Type {
        DEPOSIT, WITHDRAWAL, CHAT, COIN, EVENT
    }
}