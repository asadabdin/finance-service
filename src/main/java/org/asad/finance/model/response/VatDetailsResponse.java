package org.asad.finance.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details Related VAT")
public class VatDetailsResponse {

    @NotNull
    @JsonProperty("CountryCode")
    @ApiModelProperty(required = true, value = "Country Code of the VAT Code")
    private String countryCode;

    @JsonProperty("VatNumber")
    @ApiModelProperty(required = true, value = "VatNumber of the VAT Code")
    private String vatNumber;

    @JsonProperty("IsValid")
    @ApiModelProperty(required = true, value = "Is it a Valid VAT Code")
    private boolean isValid;

    @JsonProperty("BusinessName")
    @ApiModelProperty(required = true, value = "Business Name associated to the VAT Code")
    private String businessName;

    @JsonProperty("BusinessAddress")
    @ApiModelProperty(required = true, value = "Business Address associated to the VAT Code")
    private String businessAddress;
}
