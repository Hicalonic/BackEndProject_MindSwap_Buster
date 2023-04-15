package org.mindswap.utils.EMAILAPI;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.mindswap.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public void sendEmailWithAttachment(String email, Invoice invoice) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        message.setFrom("mindswap5backenproject@gmail.com");
        helper.setTo(email);
        helper.setSubject("MOVIE SWAP BUSTER | Rental details");
        helper.setText("Hi, " + invoice.getRental().getUser().getFirstName()  + ",\n\n" +
                "We've sent you the invoice as an attachment.\n" +
                "We hope you enjoy your movie!\n\n" +
                "Best regards,\n" +
                "The Movie Swap Buster team");

        FileSystemResource file = new FileSystemResource(new File("src/main/resources/invoices/invoicePDF".concat(invoice.getId().toString()).concat(".pdf")));
        helper.addAttachment("invoicePDF".concat(invoice.getId().toString()).concat(".pdf"), file);

        javaMailSender.send(message);
    }


}
