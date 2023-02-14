package com.nisum.poc.controller;


import com.nisum.poc.helper.TaxHelper;
import com.nisum.poc.pojo.TaxationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/tax")
public class TaxController {

    @Autowired
    TaxHelper taxHelper;


    @PostMapping(value = "/calculation")
    public ResponseEntity<?> calculateTax(@RequestBody TaxationData taxationData) throws Exception {
        log.info("START :: Request Input OrderId:: {}", taxationData.getOrderId());
        ResponseEntity<?> responseEntity = taxHelper.taxCalculation(taxationData);
        log.info("END :: Response Status Code :: {}", responseEntity.getStatusCode());
        return responseEntity;
    }


}
