package com.nisum.poc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItem extends BaseResponse {

    private int sequenceNumber;
    private String productClass;
    private String upc;
    private String bpn;
    private String description;
    private int quantity;
    private String packageQuantity;
    private BigDecimal originalGrossPrice;
    private BigDecimal salesPrice;
    private Discounts discounts;
    private List<Saving> savings;
    private BigDecimal totalTax;
    private List<TaxesType> taxes;
    //private List<Tax> tax;
    private String image;
    private String smic;
    private String departmentName;
    private String volumeUnitOfMeasure;
    private BigDecimal unitVolume;
    private BigDecimal abv;
    private BigDecimal taxRate;
    private BigDecimal snapAmount;
    private boolean isSnapEligible;
    private BigDecimal weightDebitAmount;

    public boolean getIsSnapEligible() {
        return isSnapEligible;
    }
    public List<TaxesType> getTaxes() {
        TaxesType taxesType1 = new TaxesType();
        taxesType1.setTaxCode("Sales Tax");
        taxesType1.setCalculatedTax(BigDecimal.valueOf(6.46));
        TaxesType taxesType2 = new TaxesType();
        taxesType2.setTaxCode("Delivery Fee");
        taxesType2.setCalculatedTax(BigDecimal.valueOf(5.95));
        TaxesType taxesType3 = new TaxesType();
        taxesType3.setTaxCode("Service Fee");
        taxesType3.setCalculatedTax(BigDecimal.valueOf(5.00));
        TaxesType taxesType4 = new TaxesType();
        taxesType4.setTaxCode("Bag Fee");
        taxesType4.setCalculatedTax(BigDecimal.valueOf(0.35));
        List<TaxesType> taxesTypeList = new ArrayList<>();
        taxesTypeList.add(taxesType1);
        taxesTypeList.add(taxesType2);
        taxesTypeList.add(taxesType3);
        taxesTypeList.add(taxesType4);

        return taxesTypeList;
    }
}
