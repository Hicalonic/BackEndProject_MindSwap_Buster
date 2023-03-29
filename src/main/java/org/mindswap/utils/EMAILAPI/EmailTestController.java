package org.mindswap.utils.EMAILAPI;


import jakarta.mail.MessagingException;
import org.mindswap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmailTest")
    public ResponseEntity<String> sendEmailTest() {

        emailService.sendEmailToSpecificEmail("rui.rajao@hotmail.com");

        return ResponseEntity.ok("Email Sent");
    }

    @GetMapping("/sendEmailTestAttachment")
    public ResponseEntity<String> sendEmailTestAttachment() throws MessagingException {

        emailService.sendEmailWithAttachment("rui.rajao@hotmail.com");

        return ResponseEntity.ok("Email Sent");
    }


}