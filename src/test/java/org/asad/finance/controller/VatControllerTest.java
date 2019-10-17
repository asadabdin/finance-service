package org.asad.finance.controller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.asad.finance.model.request.VatValidateRequest;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static io.restassured.RestAssured.given;
import static org.asad.finance.util.StubUtil.stubVatValidatePost;
import static org.asad.finance.util.StubUtil.stubVatValidatePost400;
import static org.asad.finance.util.TestDataUtil.convertJsonFileTo;
import static org.hamcrest.Matchers.*;
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
public class VatControllerTest {

    private final String URL_POST = "/v1/api/vat/validate";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8083);
    @Value("http://localhost:${local.server.port}")
    private String urlPrefix;

    @After
    public void tearDown() throws Exception {
        wireMockRule.resetAll();
    }

    @Test
    public void test_validateVatNumber() {
        VatValidateRequest vatValidateRequest = convertJsonFileTo("json/vat-validate-request.json", VatValidateRequest.class);

        stubVatValidatePost(wireMockRule);

        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(vatValidateRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("CountryCode", is("DE"))
                .body("VatNumber", is(vatValidateRequest.getVatCode()))
                .body("IsValid", is(true))
                .body("BusinessName", notNullValue())
                .body("BusinessAddress", notNullValue());
        //@formatter:on

        wireMockRule.verify(1, postRequestedFor(urlMatching("/validate/vat/lookup")));
    }

    @Test
    public void test_validateVatNumber_fallback() {
        VatValidateRequest vatValidateRequest = convertJsonFileTo("json/vat-validate-request.json", VatValidateRequest.class);

        stubVatValidatePost400(wireMockRule);

        //@formatter:off
        given()
                //.headers(createWkdaAndUserHeaders())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .with()
                .body(vatValidateRequest)
        .when()
                .post(urlPrefix + URL_POST)
                .prettyPeek()
        .then()
                .statusCode(OK.value())
                .body("CountryCode", nullValue())
                .body("VatNumber", is(vatValidateRequest.getVatCode()))
                .body("IsValid", is(false))
                .body("BusinessName", nullValue())
                .body("BusinessAddress", nullValue());
        //@formatter:on

        wireMockRule.verify(1, postRequestedFor(urlMatching("/validate/vat/lookup")));
    }
}