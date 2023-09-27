package CryptOptima.server.global.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@EnableConfigurationProperties(MailProperties.class)
public class MailHandler {

    private final JavaMailSenderImpl sender; // MailProperties 생성자 주입 + sender를 set

    public MailHandler(MailProperties mailProperties) { // autoconfigure.mail.MailProperties -> application-mail.yml의 속성을 자동 지정.

        sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        sender.setUsername(mailProperties.getUsername());
        sender.setPassword(mailProperties.getPassword());
        sender.getJavaMailProperties().putAll(mailProperties.getProperties());
    }

    public void sendMail(String subject, String to, String html) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(html, true);

            sender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
