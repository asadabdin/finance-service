package org.asad.finance.service.fallback;

import lombok.RequiredArgsConstructor;
import org.asad.finance.client.VatClient;
import org.asad.finance.model.request.VatValidateRequest;
import org.asad.finance.model.response.VatDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class VatFallbackService implements VatClient {

    @Override
    public VatDetailsResponse validate(@RequestBody VatValidateRequest request) {
        // i Dont have any logic to deal with it.
        return VatDetailsResponse.builder()
                .isValid(false)
                .vatNumber(request.getVatCode())
                .build();
    }
}
