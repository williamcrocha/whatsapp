package br.com.williamrocha.whatsapp;

import br.com.williamrocha.whatsapp.dto.LanguageDTO;
import br.com.williamrocha.whatsapp.dto.ResponseDTO;
import br.com.williamrocha.whatsapp.dto.SendDTO;
import br.com.williamrocha.whatsapp.dto.TemplateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class SendMessageController {

    @Value("${token}")
    private String token;

    @Value("${url}")
    private String url;

    @Value("${testPhoneNumber}")
    private String testPhoneNumber;

    @PostConstruct
    public void send(){
       send("hello_world",testPhoneNumber);
    }

    void send(String template, String phoneNumber) {
        final String line = "---------------------------------------------------------------";
        log.info(line);
        log.info("Sending message template: {} to phone: {}",template,phoneNumber);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(
                                    getSendDTO(template,phoneNumber)
                                );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            ResponseDTO responseDTO = objectMapper.readValue(response.body(),ResponseDTO.class);
            log.info("Message sended. Response: {}",responseDTO);
            log.info(line);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    SendDTO getSendDTO(String template, String phoneNumber){
        return SendDTO.builder()
                .messagingProduct("whatsapp")
                .template(TemplateDTO.builder()
                        .language(
                                LanguageDTO.builder()
                                        .code("en_US")
                                        .build()
                        )
                        .name(template)
                        .build())
                .to(phoneNumber)
                .type("template")
                .build();
    }
}
