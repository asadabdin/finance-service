package org.asad.finance.service;

import org.asad.finance.client.CurrencyClient;
import org.asad.finance.error.NoSuchCurrencyDefinedException;
import org.asad.finance.model.request.CurrencyConversionRequest;
import org.asad.finance.model.response.CurrencyConversionResponse;
import org.asad.finance.model.response.CurrencyLayerLiveResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.asad.finance.util.TestDataUtil.convertJsonFileTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterServiceTest {

    @Mock
    private CurrencyClient currencyClient;

    @InjectMocks
    private CurrencyConverterService currencyConverterService;

    @Before
    public void setUp() {

    }

    @Test
    public void convertCurrency() {
        when(currencyClient.getLiveConversionRates())
                .thenReturn(convertJsonFileTo("json/client/currency-client-response.json", CurrencyLayerLiveResponse.class));

        CurrencyConversionRequest currencyConversionRequest =
                convertJsonFileTo("json/currency-conversion-request.json", CurrencyConversionRequest.class);

        CurrencyConversionResponse currencyConversionResponse = currencyConverterService.convertCurrency(currencyConversionRequest);
        assertNotNull(currencyConversionRequest);
        assertNotNull(currencyConversionResponse);
        assertNotNull(currencyConversionResponse.getAmount());
        assertNotNull(currencyConversionResponse.getCurrencyConversionRate());
        assertNotNull(currencyConversionResponse.getTargetCurrency());
        assertEquals(currencyConversionRequest.getTargetCurrency(), currencyConversionResponse.getTargetCurrency());
    }

    @Test(expected = NoSuchCurrencyDefinedException.class)
    public void convertCurrency_withNoSourceCurrencyDefined() {
        //Data
        CurrencyLayerLiveResponse currencyLayerLiveResponse =
                convertJsonFileTo("json/client/currency-client-response.json", CurrencyLayerLiveResponse.class);
        CurrencyConversionRequest currencyConversionRequest =
                convertJsonFileTo("json/currency-conversion-request.json", CurrencyConversionRequest.class);

        //Removing Source currency mapping
        assertNotNull(currencyConversionRequest);
        assertNotNull(currencyLayerLiveResponse);
        currencyLayerLiveResponse.getConversionMapping().remove(currencyConversionRequest.getSourceCurrency());
        when(currencyClient.getLiveConversionRates()).thenReturn(currencyLayerLiveResponse);

        currencyConverterService.convertCurrency(currencyConversionRequest);
    }

    @Test(expected = NoSuchCurrencyDefinedException.class)
    public void convertCurrency_withNoTargetCurrencyDefined() {
        //Data
        CurrencyLayerLiveResponse currencyLayerLiveResponse =
                convertJsonFileTo("json/client/currency-client-response.json", CurrencyLayerLiveResponse.class);
        CurrencyConversionRequest currencyConversionRequest =
                convertJsonFileTo("json/currency-conversion-request.json", CurrencyConversionRequest.class);

        //Removing Target currency mapping
        assertNotNull(currencyConversionRequest);
        assertNotNull(currencyLayerLiveResponse);
        currencyLayerLiveResponse.getConversionMapping().remove(currencyConversionRequest.getTargetCurrency());
        when(currencyClient.getLiveConversionRates()).thenReturn(currencyLayerLiveResponse);

        currencyConverterService.convertCurrency(currencyConversionRequest);
    }
}
