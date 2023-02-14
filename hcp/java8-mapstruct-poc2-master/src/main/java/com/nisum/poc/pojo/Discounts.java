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
public class Discounts {

    private BigDecimal retailDiscount;

    private BigDecimal manufacturerDiscount;

    public Discounts (Discounts discounts) {
        this.retailDiscount = discounts.getRetailDiscount();
        this.manufacturerDiscount = discounts.getManufacturerDiscount();
    }

}
