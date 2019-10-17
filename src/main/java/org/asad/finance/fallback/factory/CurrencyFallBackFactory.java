package org.asad.finance.fallback.factory;

import feign.hystrix.FallbackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.finance.client.CurrencyClient;
import org.asad.finance.service.fallback.CurrencyFallbackService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyFallBackFactory implements FallbackFactory<CurrencyClient> {

    private final CurrencyFallbackService currencyFallbackService;

    @Override
    public CurrencyClient create(Throwable cause) {
        log.warn("Currency converting using fallback service : {}", cause);
        return currencyFallbackService;
    }
}
