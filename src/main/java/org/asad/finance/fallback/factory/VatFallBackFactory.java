package org.asad.finance.fallback.factory;

import feign.hystrix.FallbackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.finance.client.VatClient;
import org.asad.finance.service.fallback.VatFallbackService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VatFallBackFactory implements FallbackFactory<VatClient> {

    private final VatFallbackService vatFallBackService;

    @Override
    public VatClient create(Throwable cause) {
        log.warn("vat validating using fallback service : {}", cause);
        return vatFallBackService;
    }
}
