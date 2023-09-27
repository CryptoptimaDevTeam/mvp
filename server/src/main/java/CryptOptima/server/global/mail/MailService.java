package CryptOptima.server.global.mail;

import CryptOptima.server.domain.deposit.entity.DepositRecord;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final TemplateEngine templateEngine;
    private final MailHandler mailHandler;

    public void sendWelcomeMail(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());

        Context context = new Context();
        context.setVariables(map);

        String html = templateEngine.process("welcome", context);
        mailHandler.sendMail("Welcome to Cryptoptima!", user.getAccountId(), html);
    }

    public void sendDepositMail(DepositRecord deposit) {

    }

    public void sendWithdrawalMail(WithdrawalRecord withdrawal) {

    }

    public void sendPromotionMail(List<User> users) {

    }
}
