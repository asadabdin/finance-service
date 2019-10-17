package org.asad.finance.client;

import org.asad.finance.fallback.factory.CurrencyFallBackFactory;
import org.asad.finance.model.response.CurrencyLayerLiveResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "currency-client", fallbackFactory = CurrencyFallBackFactory.class)
public interface CurrencyClient {

    @Cacheable(cacheNames = "currency-live")
    @GetMapping(path = "/live?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1", produces = APPLICATION_JSON_VALUE)
    CurrencyLayerLiveResponse getLiveConversionRates();

}
