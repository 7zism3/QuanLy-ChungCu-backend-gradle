package com.nhom43.quanlychungcubackendgradle.admin.server;


import com.nhom43.quanlychungcubackendgradle.admin.model.NotificationEmail;
import com.nhom43.quanlychungcubackendgradle.ex.SpringException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("support@chungcuABC.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Đã gửi email kích hoạt!");
        } catch (MailException e) {
            throw new SpringException("Đã xảy ra ngoại lệ khi gửi thư đến " + notificationEmail.getRecipient()
                    + ". Nếu bạn dùng gmail. Hãy kiểm tra Cài đặt bảo mật tài khoản và bật cho phép ứng dụng không tin tưởng!");
        }
    }

}
