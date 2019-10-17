package org.asad.finance.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.finance.client.CurrencyClient;
import org.asad.finance.error.NoSuchCurrencyDefinedException;
import org.asad.finance.model.CurrencyCode;
import org.asad.finance.model.request.CurrencyConversionRequest;
import org.asad.finance.model.response.CurrencyConversionResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

import static java.util.Optional.of;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyConverterService {

    private final CurrencyClient currencyClient;

    public CurrencyConversionResponse convertCurrency(@NonNull CurrencyConversionRequest currencyConversionRequest) {
        log.info("Converting currency {}", currencyConversionRequest);
        Map<CurrencyCode, Double> conversionRate = currencyClient.getLiveConversionRates().getConversionMapping();
        return of(currencyConversionRequest)
                .map(CurrencyConversionRequest::getSourceCurrency)
                .filter(conversionRate::containsKey)
                .map(conversionRate::get)
                .map(convertIntoDollar(currencyConversionRequest))
                .map(convertIntoTargetCurrency(currencyConversionRequest, conversionRate))
                .orElseThrow(NoSuchCurrencyDefinedException::new);
    }

    private Function<Double, CurrencyConversionResponse> convertIntoTargetCurrency(@NonNull CurrencyConversionRequest currencyConversionRequest, Map<CurrencyCode, Double> conversionRate) {
        return dollar -> of(currencyConversionRequest)
                .map(CurrencyConversionRequest::getTargetCurrency)
                .filter(conversionRate::containsKey)
                .map(conversionRate::get)
                .map(rate -> rate * dollar)
                .map(amount -> CurrencyConversionResponse.builder()
                        .amount(amount)
                        .currencyConversionRate(getConversionRate(currencyConversionRequest, conversionRate))
                        .targetCurrency(currencyConversionRequest.getTargetCurrency())
                        .build()
                )
                .orElseThrow(NoSuchCurrencyDefinedException::new);
    }

    private Function<Double, Double> convertIntoDollar(@NonNull CurrencyConversionRequest currencyConversionRequest) {
        return rate -> rate * currencyConversionRequest.getAmount();
    }

    private Double getConversionRate(@NonNull CurrencyConversionRequest currencyConversionRequest, Map<CurrencyCode, Double> conversionRate) {
        return of(currencyConversionRequest)
                .map(CurrencyConversionRequest::getSourceCurrency)
                .map(conversionRate::get)
                .map(dollarRate -> conversionRate.get(currencyConversionRequest.getTargetCurrency()))
                .orElseThrow(NoSuchCurrencyDefinedException::new);
    }
}
