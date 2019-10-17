package org.asad.finance.service.fallback;

import org.asad.finance.config.properties.CurrencyConversionProperties;
import org.asad.finance.model.response.CurrencyLayerLiveResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.asad.finance.util.TestDataUtil.convertJsonFileTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyFallbackServiceTest {

    @Mock
    private CurrencyConversionProperties currencyConversionProperties;

    @InjectMocks
    private CurrencyFallbackService currencyFallbackService;

    @Before
    public void setUp() {
        when(currencyConversionProperties.getConversionRate()).thenReturn(
                convertJsonFileTo("json/properties/currency-property.json", CurrencyConversionProperties.class)
                        .getConversionRate()
        );
    }

    @Test
    public void getLiveConversionRates() {
        CurrencyLayerLiveResponse liveConversionRates = currencyFallbackService.getLiveConversionRates();
        assertNotNull(liveConversionRates);
        assertTrue(liveConversionRates.isSuccess());
        assertNotNull(liveConversionRates.getConversionMapping());
        assertFalse(liveConversionRates.getConversionMapping().isEmpty());
    }
}
