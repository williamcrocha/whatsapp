package br.com.williamrocha.whatsapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendDTO {

    @JsonProperty(value = "messaging_product")
    private String messagingProduct;
    @JsonProperty("template")
    private TemplateDTO template;
    @JsonProperty("to")
    private String to;
    @JsonProperty("type")
    private String type;

}
