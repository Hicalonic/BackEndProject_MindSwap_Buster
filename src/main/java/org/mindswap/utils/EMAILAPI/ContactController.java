/*package org.mindswap.utils.EMAILAPI;

import com.demo.gmailAPIClient.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping(path = "/request")
    public void submitContactRequest(
            @RequestParam("subject") String subject,
            @RequestParam("description") String description,
            @RequestBody MultipartFile file) {
        contactService.submitContactRequest(subject, description, file);
    }

}

 */
