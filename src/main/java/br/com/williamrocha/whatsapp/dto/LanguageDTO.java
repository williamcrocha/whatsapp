package br.com.williamrocha.whatsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LanguageDTO {

    @JsonProperty("code")
    private String code;

}
