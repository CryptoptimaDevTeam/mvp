package CryptOptima.server.global.mail;

import CryptOptima.server.domain.deposit.entity.DepositRecord;
import CryptOptima.server.domain.user.entity.User;
import CryptOptima.server.domain.userexchange.entity.UserExchange;
import CryptOptima.server.domain.withdrawal.entity.WithdrawalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final TemplateEngine templateEngine;
    private final MailHandler mailHandler;

    public void sendWelcomeMail(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("referralCode", user.getReferralCode());

        Context context = new Context();
        context.setVariables(map);

        String html = templateEngine.process("welcome", context);
        mailHandler.sendMail("Welcome to Cryptoptima!", user.getAccountId(), html);
    }

    public void sendDepositMail(DepositRecord deposit) {

    }

    public void sendWithdrawalMail(WithdrawalRecord withdrawal) {

    }

    public void sendReferralPromotionMail(User user) {

    }

    public void sendUidConfirmationMail(UserExchange userExchange, String subject) {

        Map<String, Object> map = new HashMap<>();
        map.put("username", userExchange.getUser().getUsername());
        map.put("userExchange", userExchange);

        Context context = new Context();
        context.setVariables(map);

        String html = templateEngine.process("uid-confirmation", context);
        mailHandler.sendMail(subject, userExchange.getUser().getAccountId(), html);
    }

}
