package org.asad.finance.service.fallback;

import org.asad.finance.model.request.VatValidateRequest;
import org.asad.finance.model.response.VatDetailsResponse;
import org.asad.finance.util.TestDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class VatFallbackServiceTest {

    @InjectMocks
    private VatFallbackService service;

    @Test
    public void validate() {
        VatValidateRequest vatValidateRequest = TestDataUtil.convertJsonFileTo("json/vat-validate-request.json", VatValidateRequest.class);

        VatDetailsResponse validate = service.validate(vatValidateRequest);
        assertNotNull(validate);
        assertEquals(validate.getVatNumber(), vatValidateRequest.getVatCode());
        assertFalse(validate.isValid());
    }
}