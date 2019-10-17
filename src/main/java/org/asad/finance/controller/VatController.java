package org.asad.finance.controller;

import lombok.RequiredArgsConstructor;
import org.asad.finance.client.VatClient;
import org.asad.finance.model.request.VatValidateRequest;
import org.asad.finance.model.response.VatDetailsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "vat-controller", path = {"/v1/api/vat"})
public class VatController {

    private final VatClient vatClient;

    @PostMapping(path = "/validate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public VatDetailsResponse validateVatNumber(@Valid @RequestBody VatValidateRequest request) {
        return vatClient.validate(request);
    }
}
