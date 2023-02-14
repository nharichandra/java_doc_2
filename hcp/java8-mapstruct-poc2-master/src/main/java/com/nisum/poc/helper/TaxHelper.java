package com.nisum.poc.helper;

import com.nisum.poc.enums.TaxEnum;
import com.nisum.poc.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class TaxHelper {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> taxCalculation(TaxationData taxationData) throws IOException {

        ResponseEntity<?> taxationDataResponse;
        ResponseEntity<TaxationData> taxationDataResponseEntity;
        try {
            taxationDataResponseEntity = restTemplate.postForEntity("http://localhost:9091/v1/tax", taxationData, TaxationData.class);
            HashMap detailedTaxesMap = new HashMap();
            taxationData.setTax(generateTaxes(taxationDataResponseEntity.getBody(), taxationData.getCharges(), detailedTaxesMap));
            taxationDataResponse = new ResponseEntity<>(taxationData, HttpStatus.OK);

        } catch (Exception exception) {

            taxationDataResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return taxationDataResponse;
    }

    private static void setTotal(BigDecimal totalTax, Taxes taxes, List<TaxDetails> details) {
        BigDecimal deliveryFee = BigDecimal.ZERO;
        Optional<TaxDetails> deliveryFees = details.stream()
                .filter(detail -> detail.getKey().equalsIgnoreCase(TaxEnum.DELIVERY_FEE.getTaxName())).findAny();
        if (deliveryFees.isPresent()) {
            deliveryFee = deliveryFees.get().getValue();
        }
        taxes.setTotal(totalTax.add(deliveryFee));
    }

    private static List<TaxDetails> generateDetails(Map<String, BigDecimal> taxesMap, Charges charges) {
        ArrayList details = new ArrayList();
        int index = 1;
        while (index <= TaxEnum.totalValues()) {
            String taxName = TaxEnum.getTaxName(index);
            if (taxName.equalsIgnoreCase("Delivery Fee")) {
                processDeliveryFee(details, charges);
            }
            if (taxName.equalsIgnoreCase("Service Fee")) {
                processServiceFee(details, charges);
            }
            BigDecimal taxAmount = taxesMap.get(taxName);
            if (taxAmount != null) {
                updateTaxDetails(taxName, taxAmount, details);
            }
            index++;
        }

        return details;
    }

    private static void updateTaxDetails(String taxName, BigDecimal taxAmount, List<TaxDetails> details) {
        TaxDetails taxDetails = new TaxDetails(taxName, taxAmount);
        if (TaxEnum.specificTaxes().contains(taxName)) {
            if (taxName.equalsIgnoreCase(TaxEnum.SALES_TAX.getTaxName())) {
                details.add(taxDetails);
            }
        } else {
            if (isValidAmount(taxAmount)) {
                details.add(taxDetails);
            }
        }
    }

    public static boolean isValidAmount(BigDecimal amount) {
        boolean isValidValue = false;
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            isValidValue = true;
        }
        return isValidValue;
    }

    private static void processDeliveryFee(List<TaxDetails> details, Charges charges) {
        if (null != charges && null != charges.getShipping()) {
            Shipping shipping = charges.getShipping();
            BigDecimal salesPrice = shipping.getSalesPrice();
            BigDecimal originalPrice = shipping.getOriginalPrice();
            TaxDetails taxDetails = new TaxDetails();
            taxDetails.setKey(TaxEnum.DELIVERY_FEE.getTaxName());
            if (salesPrice.compareTo(BigDecimal.ZERO) == 0 && originalPrice.compareTo(BigDecimal.ZERO) == 0) {
                taxDetails.setValue(BigDecimal.ZERO);
            } else if (salesPrice.compareTo(originalPrice) == 0) {
                taxDetails.setValue(salesPrice);
            } else {
                taxDetails.setValue(salesPrice);
                taxDetails.setPrevious(originalPrice);
            }
            details.add(taxDetails);
        }
    }

    private static void processServiceFee(List<TaxDetails> details, Charges charges) {
        if (null != charges && null != charges.getServiceFee()) {
            ServiceFee serviceFee = charges.getServiceFee();
            BigDecimal salesPrice = serviceFee.getSalesPrice();
            BigDecimal originalPrice = serviceFee.getOriginalPrice();
            TaxDetails taxDetails = new TaxDetails();
            taxDetails.setKey(TaxEnum.SERVICE_FEE.getTaxName());
            if (salesPrice.compareTo(BigDecimal.ZERO) == 0 && originalPrice.compareTo(BigDecimal.ZERO) == 0) {
                taxDetails.setValue(BigDecimal.ZERO);
            } else if (salesPrice.compareTo(originalPrice) == 0) {
                taxDetails.setValue(salesPrice);
            } else {
                taxDetails.setValue(salesPrice);
                taxDetails.setPrevious(originalPrice);
            }
            details.add(taxDetails);
        }
    }


    private Taxes generateTaxes(TaxationData taxationData, Charges charges, Map<Long, Map<String, BigDecimal>> detailedTaxesMap) {
        {
            Taxes taxes = new Taxes();
            List<LineItem> lineItems = taxationData.getLineItems();
            Map<String, BigDecimal> taxesMap = generateTaxesMap(lineItems, detailedTaxesMap);
            List<TaxDetails> details = generateDetails(taxesMap, charges);
            BigDecimal totalTax = details.stream()
                    .filter(detail -> !detail.getKey().equalsIgnoreCase(TaxEnum.DELIVERY_FEE.getTaxName()))
                    .map(TaxDetails::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
            setTotal(totalTax, taxes, details);
            taxes.setDetails(details);
            return taxes;
        }
    }

    private Map<String, BigDecimal> generateTaxesMap(List<LineItem> lineItems, Map<Long, Map<String, BigDecimal>> detailedTaxesMap) {
        Map<String, BigDecimal> taxesMap = Taxes.getInitialTaxesMap();
        for (LineItem lineItem : lineItems) {
            if (CollectionUtils.isNotEmpty(lineItem.getTaxes())) {
                lineItem.getTaxes().forEach(taxesType -> {
                    if (null != taxesType.getCalculatedTax()) {
                        taxesType.setCalculatedTax(taxesType.getCalculatedTax()
                                .setScale(2, RoundingMode.HALF_EVEN));
                    }
                    if (null != lineItem.getTotalTax()) {
                        lineItem.setTotalTax(lineItem.getTotalTax().setScale(2, RoundingMode.HALF_EVEN));
                    }
                });
            }
            Map<String, BigDecimal> taxMap = lineItem.getTaxes().stream()
                    .collect(Collectors.groupingBy(TaxesType::getTaxCode,
                            Collectors.mapping(TaxesType::getCalculatedTax,
                                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

            taxesMap = Stream.concat(taxMap.entrySet().stream(), taxesMap.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            BigDecimal::add
                            )
                    );
        }
        return taxesMap;
    }


}
