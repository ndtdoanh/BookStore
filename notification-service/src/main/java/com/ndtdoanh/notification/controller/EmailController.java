package com.ndtdoanh.notification.controller;

import com.ndtdoanh.notification.dto.ApiResponse;
import com.ndtdoanh.notification.dto.request.EmailRequest;
import com.ndtdoanh.notification.dto.request.SendEmailRequest;
import com.ndtdoanh.notification.dto.response.EmailResponse;
import com.ndtdoanh.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    EmailService emailService;

    @PostMapping("/email/send")
    ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest request) {
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(request))
                .build();
    }
}
