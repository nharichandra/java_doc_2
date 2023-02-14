package com.nisum.poc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxationData {

    private String banner;

    private String type;

    private String salesDate;

    private String orderId;

    private Addresses addresses;

    private String fulfillmentType;

    private Taxes tax;

    private List<LineItem> lineItems;

    private String customerId;

    private Charges charges;

    private String traceId;

    private BigDecimal snapAmount;
}
