package com.nisum.poc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nisum.poc.enums.TaxEnum;
import lombok.*;
import org.apache.commons.collections4.map.HashedMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Taxes {

    private BigDecimal total;
    private List<TaxDetails> details;

    public static Map<String, BigDecimal> getInitialTaxesMap() {
        Map<String, BigDecimal> taxesMap = new HashedMap<>();
        TaxEnum.specificTaxes().forEach(tax -> taxesMap.put(tax, BigDecimal.ZERO));
        return taxesMap;
    }
}

