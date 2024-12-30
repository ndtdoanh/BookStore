package com.ndtdoanh.notification.service;

import com.ndtdoanh.notification.dto.request.EmailRequest;
import com.ndtdoanh.notification.dto.request.SendEmailRequest;
import com.ndtdoanh.notification.dto.request.Sender;
import com.ndtdoanh.notification.dto.response.EmailResponse;
import com.ndtdoanh.notification.exception.AppException;
import com.ndtdoanh.notification.exception.ErrorCode;
import com.ndtdoanh.notification.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    String apiKey = System.getenv("BREVO_APIKEY");

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("ndtdoanh dotcom")
                        .email("snguk2@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();

        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }

    }
}