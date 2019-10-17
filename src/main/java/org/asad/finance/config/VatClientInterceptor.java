package org.asad.finance.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VatClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("apikey", "28e63794-ef8a-4616-80bb-26fdd3709a19");
    }

}
