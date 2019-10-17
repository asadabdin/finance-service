package org.asad.finance.service.fallback;

import lombok.RequiredArgsConstructor;
import org.asad.finance.client.CurrencyClient;
import org.asad.finance.config.properties.CurrencyConversionProperties;
import org.asad.finance.model.response.CurrencyLayerLiveResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyFallbackService implements CurrencyClient {

    private final CurrencyConversionProperties currencyConversionProperties;

    @Override
    public CurrencyLayerLiveResponse getLiveConversionRates() {
        return CurrencyLayerLiveResponse.builder()
                .success(true)
                .conversionMapping(currencyConversionProperties.getConversionRate())
                .build();
    }

}
