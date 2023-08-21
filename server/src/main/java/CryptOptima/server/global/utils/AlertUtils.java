package CryptOptima.server.global.utils;

import CryptOptima.server.domain.alert.dto.AlertDto;
import CryptOptima.server.domain.alert.dto.AlertMapper;
import CryptOptima.server.domain.alert.entity.Alert;
import CryptOptima.server.domain.alert.service.AlertService;
import CryptOptima.server.global.event.PushAlertEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class AlertUtils {

//    SSE Connection 체결된 SseEmitter들을 관리하는 리스트
//    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final AlertService alertService;
    private final AlertMapper alertMapper;

//    연결된 클라이언트를 추가한다.
//    public SseEmitter add(SseEmitter sseEmitter) { // 추가가 되고 수행 완료하면 알아서 제거까지 된다는 의미인건가??
//        this.emitters.add(sseEmitter);
//        sseEmitter.onCompletion(() -> {
//            this.emitters.remove(sseEmitter); // emitter가 더이상 필요 없어지면 삭제한다.
//        });
//        sseEmitter.onTimeout(() -> {
//            sseEmitter.complete();
//        });
//        return sseEmitter;
//    }

//    public void test() {
//        this.emitters.forEach(sseEmitter -> {
//            try {
//                sseEmitter.send(SseEmitter.event().data("Data").name("test"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }

    // 1. 이벤트를 받는다.
    @EventListener
    public void method(PushAlertEvent event) {
        String title = getTitle(event); // 지역 변수
        String content = getContent(event); // 지역 변수

        // (1) Alert 엔티티저장
        if(event.getEventEntity() != null) {
            // Alert Entity 저장
            Alert savedAlert = alertService.createAlert(event.getUserId(), title, content);

            // entity를 바탕으로 DTO를 생성
            AlertDto.AlertPushDto alertPushDto = alertMapper.alertToAlertPushDto(savedAlert);
            sendAlert(alertPushDto);
        }
        else { // (2) AlertDto 바로 전송
            AlertDto.AlertPushDto alertPushDto = alertMapper.alertEventToAlertPushDto(title, content);
            sendAlert(alertPushDto);
        }
    }


    // Todo 3. 알림 내용 데이터를 지정한다. (type 별 알림 content 작성 StringBuilder)
    private String getTitle(PushAlertEvent event) {
        switch(event.getType()) {
            case DEPOSIT:
                return "DEPOSIT";
            case WITHDRAWAL:
                return "WITHDRAWAL";
            case CHAT:
                return "CHAT";
            case COIN:
                return "COIN";
            case EVENT:
                return "EVENT";
        }
        return "";
    }

    private String getContent(PushAlertEvent event) {
        switch(event.getType()) {
            case DEPOSIT:
                return "DEPOSIT";
            case WITHDRAWAL:
                return "WITHDRAWAL";
            case CHAT:
                return "CHAT";
            case COIN:
                return "COIN";
            case EVENT:
                return "EVENT";
        }
        return "";
    }

    // Todo 4. 알림을 전송한다. (sse 전송)
    private void sendAlert(AlertDto.AlertPushDto alertPushDto) {
//        System.out.println(alertPushDto.getTitle() + alertPushDto.getContent() + alertPushDto.getPubDate());
    }
}