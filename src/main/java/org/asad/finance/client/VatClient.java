package org.asad.finance.client;

import org.asad.finance.fallback.factory.VatFallBackFactory;
import org.asad.finance.model.request.VatValidateRequest;
import org.asad.finance.model.response.VatDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "vat-client", fallbackFactory = VatFallBackFactory.class)
public interface VatClient {

    @PostMapping(path = "/validate/vat/lookup", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    VatDetailsResponse validate(@RequestBody VatValidateRequest request);

}
