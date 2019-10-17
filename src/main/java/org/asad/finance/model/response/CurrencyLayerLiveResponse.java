package org.asad.finance.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.asad.finance.model.CurrencyCode;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyLayerLiveResponse {

    private boolean success;

    @JsonProperty("quotes")
    private Map<CurrencyCode, Double> conversionMapping;
}
