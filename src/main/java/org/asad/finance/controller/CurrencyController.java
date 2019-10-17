package org.asad.finance.controller;

import lombok.RequiredArgsConstructor;
import net.rossillo.spring.web.mvc.CacheControl;
import org.asad.finance.model.request.CurrencyConversionRequest;
import org.asad.finance.model.response.CurrencyConversionResponse;
import org.asad.finance.service.CurrencyConverterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static net.rossillo.spring.web.mvc.CachePolicy.MUST_REVALIDATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "currency-controller", path = {"/v1/api/currency"})
public class CurrencyController {

    private final CurrencyConverterService currencyConverterService;

    @CacheControl(maxAge = 10, policy = MUST_REVALIDATE)
    @PostMapping(path = "/convert", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CurrencyConversionResponse validateVatNumber(@Valid @RequestBody CurrencyConversionRequest request) {
        return currencyConverterService.convertCurrency(request);
    }
}
