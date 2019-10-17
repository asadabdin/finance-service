package org.asad.finance.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.asad.finance.model.CurrencyCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "asad.currency.conversion")
public class CurrencyConversionProperties {

    private Map<CurrencyCode, Double> conversionRate;

}
