package org.asad.finance.controller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.asad.finance.model.request.CurrencyConversionRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static io.restassured.RestAssured.given;
import static org.asad.finance.util.StubUtil.stubCurrencyLiveGet;
import static org.asad.finance.util.StubUtil.stubCurrencyLiveGet400;
import static org.asad.finance.util.TestDataUtil.convertJsonFileTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,
        properties = {
                "vat-client.ribbon.listOfServers: localhost:8083",
                "currency-client.ribbon.listOfServers: localhost:8083"
        }
)
public class CurrencyControllerTest {

    @Autowired
    private CacheManager cacheManager;

    private final String URL_POST = "/v1/api/currency/convert";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8083);
    @Value("http://localhost:${local.server.port}")
    private String urlPrefix;

    @Before
    public void setUp() {
        cacheManager.getCacheNames().stream()
                .map(cacheManager::getCache)
                .filter(Objects::nonNull)
                .forEach(Cache::clear);
    }

    @After
    public void tearDown() {
        wireMockRule.resetAll();
    }

    @Test
    public void convert_test() {
        CurrencyConversionRequest currencyConversionRequest = convertJsonFileTo("json/currency-conversion-request.json", CurrencyConversionRequest.class);
        stubCurrencyLiveGet(wireMockRule);

        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(currencyConversionRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("amount", notNullValue())
                .body("targetCurrency", is(currencyConversionRequest.getTargetCurrency().getDescription()))
                .body("currencyConversionRate", notNullValue());
        //@formatter:on

        wireMockRule.verify(1, getRequestedFor(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1")));
    }

    @Test
    public void convert_test_cache() throws InterruptedException {
        CurrencyConversionRequest currencyConversionRequest = convertJsonFileTo("json/currency-conversion-request.json", CurrencyConversionRequest.class);
        stubCurrencyLiveGet(wireMockRule);

        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(currencyConversionRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("amount", notNullValue())
                .body("targetCurrency", is(currencyConversionRequest.getTargetCurrency().getDescription()))
                .body("currencyConversionRate", notNullValue());
        //@formatter:on

        wireMockRule.verify(1, getRequestedFor(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1")));
        wireMockRule.resetRequests();

        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(currencyConversionRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("amount", notNullValue())
                .body("targetCurrency", is(currencyConversionRequest.getTargetCurrency().getDescription()))
                .body("currencyConversionRate", notNullValue());
        //@formatter:on

        wireMockRule.verify(0, getRequestedFor(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1")));
        wireMockRule.resetRequests();
        Thread.sleep(10000);


        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(currencyConversionRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("amount", notNullValue())
                .body("targetCurrency", is(currencyConversionRequest.getTargetCurrency().getDescription()))
                .body("currencyConversionRate", notNullValue());
        //@formatter:on

        wireMockRule.verify(1, getRequestedFor(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1")));
    }

    @Test
    public void convert_test_fallback() {
        CurrencyConversionRequest currencyConversionRequest = convertJsonFileTo("json/currency-conversion-request.json", CurrencyConversionRequest.class);
        stubCurrencyLiveGet400(wireMockRule);

        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(currencyConversionRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("amount", notNullValue())
                .body("targetCurrency", is(currencyConversionRequest.getTargetCurrency().getDescription()))
                .body("currencyConversionRate", notNullValue());
        //@formatter:on

        wireMockRule.verify(1, getRequestedFor(urlMatching("/live\\?access_key=cc160e7f08e46f410047a93f3024b5d9&format=1")));
    }

}
