package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.MailReq;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
    @Value("${mail.sender}")
    private String from;

    private final JavaMailSender mailSender;
    private final MessaggiService msgS;

    public void sendMail(MailReq req) throws Exception {
        if (req.getTo() == null || req.getOggetto() == null || req.getBody() == null)
            throw new LibriException(msgS.get("mail_error"));

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "UTF-8");

        helper.setTo(req.getTo());
        helper.setFrom(from);
        helper.setSubject(req.getOggetto());
        helper.setText(req.getBody(), true);
        mailSender.send(mimeMessage);
    }
}
