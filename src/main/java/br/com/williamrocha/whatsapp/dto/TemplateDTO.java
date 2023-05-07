package br.com.williamrocha.whatsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemplateDTO {

    @JsonProperty("language")
    private LanguageDTO language;
    @JsonProperty("name")
    private String name;
}
