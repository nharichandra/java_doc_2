package com.nisum.poc.controller;

import com.nisum.poc.helper.TaxHelper;
import com.nisum.poc.pojo.TaxationData;
import com.nisum.poc.util.StubUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaxControllerTest {

    @InjectMocks
    TaxController taxController;

    @Mock
    TaxHelper taxHelper;

    MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taxController = new TaxController();
        ReflectionTestUtils.setField(taxController, "taxHelper", taxHelper);
        mockMvc = MockMvcBuilders.standaloneSetup(taxController).build();
    }
    @Test
    void calculateTax() throws Exception {
        String taxationDataRequest = StubUtil.getTaxationDataJson("request");
        String taxationDataResponse = StubUtil.getTaxationDataJson("response");

        when(taxHelper.taxCalculation(any(TaxationData.class)))
                .thenReturn(new ResponseEntity(StubUtil.getTaxationData("response"), HttpStatus.OK));

        mockMvc.perform(post("/v1/tax/calculation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taxationDataRequest))
                .andExpect(status().isOk())
                .andExpect(content().string(taxationDataResponse));
    }
}
