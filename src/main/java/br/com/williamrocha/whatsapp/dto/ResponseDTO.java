package br.com.williamrocha.whatsapp.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {

    @JsonProperty("messaging_product")
    private String messagingProduct;
    @JsonProperty("contacts")
    private List<ContactDTO> contacts;
    @JsonProperty("messages")
    private List<MessageDTO> messages;
}
