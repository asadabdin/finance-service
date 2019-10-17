package org.asad.finance.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "Request to Validate VAT")
public class VatValidateRequest {

    @NotNull
    @JsonProperty("VatCode")
    @ApiModelProperty(required = true, value = "Vat code which needs to be validated")
    private String vatCode;
}
