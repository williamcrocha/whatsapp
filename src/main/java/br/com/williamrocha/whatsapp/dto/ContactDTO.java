package br.com.williamrocha.whatsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDTO {

    @JsonProperty("input")
    private String input;
    @JsonProperty("wa_id")
    private String waId;

}
