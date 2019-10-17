package org.asad.finance.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.asad.finance.model.CurrencyCode;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel(description = "Request to Covert a currency")
public class CurrencyConversionResponse {

    @NotNull
    @ApiModelProperty("Converted Amount")
    private Double amount;

    @NotNull
    @ApiModelProperty("Conversion rate applied")
    private Double currencyConversionRate;

    @NotNull
    @ApiModelProperty("Target Currency")
    private CurrencyCode targetCurrency;

}
