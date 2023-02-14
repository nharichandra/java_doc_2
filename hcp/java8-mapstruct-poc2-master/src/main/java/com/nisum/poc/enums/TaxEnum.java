package com.nisum.poc.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum TaxEnum {

    SALES_TAX("Sales Tax", 1),
    SPIRITS_SALES_TAX("Spirits Sales Tax", 2),
    TOBACCO_SALES_TAX("Tobacco Sales Tax", 3),
    BEVERAGE_TAX("Beverage Tax", 4),
    CHICAGO_BOTTLED_WATER_TAX("Chicago Bottled Water Tax", 5),
    CIGARETTE_TAX("Cigarette Tax", 6),
    LIQUOR_TAX("Liquor Tax", 7),
    PACKAGED_LIQUOR_TAX("Packaged Liquor Tax", 8),
    SPIRITS_LITER_TAX("Spirits Liter Tax", 9),
    SWEETENED_BEVERAGE_TAX("Sweetened Beverage Tax", 10),
    DELIVERY_FEE("Delivery Fee", 11),
    SERVICE_FEE("Service Fee", 12),
    BAG_FEE("Bag Fee", 13),
    CIGARETTE_FEE("Cigarette Fee", 14),
    CONTAINER_FEE("Container Fee", 15),
    CONTAINER_DEPOSIT("Container Deposit", 16),
    SUBSCRIPTION_FEE("Subscription Fee", 17);

    private final String taxName;
    private final Integer taxIndex;

    TaxEnum(String taxName, int taxIndex) {
        this.taxName = taxName;
        this.taxIndex = taxIndex;
    }

    public static String getTaxName(int taxIndex) {
        String taxName = "";
        for (TaxEnum taxEnum : TaxEnum.values()) {
            if (taxIndex == taxEnum.getTaxIndex()) {
                taxName = taxEnum.getTaxName();
            }
        }
        return taxName;
    }

    public static int totalValues() {
        return TaxEnum.values().length;
    }

    public static List<String> specificTaxes() {
        return new ArrayList<>(Arrays.asList(SALES_TAX.taxName, DELIVERY_FEE.taxName));
    }

    public static List<String> allTaxTypes() {
        return new ArrayList<>(Arrays.asList(
                SALES_TAX.taxName,
                DELIVERY_FEE.taxName,
                SPIRITS_SALES_TAX.taxName,
                TOBACCO_SALES_TAX.taxName,
                BEVERAGE_TAX.taxName,
                CHICAGO_BOTTLED_WATER_TAX.taxName,
                CIGARETTE_TAX.taxName,
                LIQUOR_TAX.taxName,
                PACKAGED_LIQUOR_TAX.taxName,
                SPIRITS_LITER_TAX.taxName,
                SWEETENED_BEVERAGE_TAX.taxName,
                BAG_FEE.taxName,
                SERVICE_FEE.taxName,
                CIGARETTE_FEE.taxName,
                CONTAINER_FEE.taxName,
                CONTAINER_DEPOSIT.taxName));
    }

}
