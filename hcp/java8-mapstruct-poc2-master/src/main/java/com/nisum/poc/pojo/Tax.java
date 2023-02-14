package com.nisum.poc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tax {

    private String taxResult;

    private String taxType;

    private String situs;

    private String taxCollectedFromParty;

    private String taxStructure;

    private Jurisdiction jurisdiction;

    private BigDecimal calculatedTax;

    private String effectiveRate;

    private Taxable taxable;

    private Imposition imposition;

    private ImpositionType impositionType;

    private String taxRuleId;

    private String basisRuleId;

    private String nominalRate;

}

