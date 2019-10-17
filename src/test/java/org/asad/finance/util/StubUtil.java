package org.asad.finance.util;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RegexPattern;
import io.restassured.RestAssured;
import lombok.experimental.UtilityClass;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.asad.finance.util.TestDataUtil.readResourceToString;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@UtilityClass
public final class StubUtil {

    public static final String REGEX_UUID = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static void stubCurrencyLiveGet(WireMockRule service) {
        service.stubFor(get(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(readResourceToString("json/client/currency-client-response.json")))
        );
    }

    public static void stubVatValidatePost(WireMockRule service) {
        service.stubFor(post(urlMatching("/validate/vat/lookup"))
                .withHeader("apikey", new RegexPattern(REGEX_UUID))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(readResourceToString("json/client/vat-validate-response.json"))));
    }

    public static void stubCurrencyLiveGet400(WireMockRule service) {
        service.stubFor(get(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1"))
                .willReturn(aResponse()
                        .withStatus(NOT_FOUND.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        );
    }

    public static void stubVatValidatePost400(WireMockRule service) {
        service.stubFor(post(urlMatching("/validate/vat/lookup"))
                .withHeader("apikey", new RegexPattern(REGEX_UUID))
                .willReturn(aResponse()
                        .withStatus(NOT_FOUND.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)));
    }

}
