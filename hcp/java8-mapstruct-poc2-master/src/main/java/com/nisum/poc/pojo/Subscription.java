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
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Subscription extends BaseResponse {

    private String upc;
    private String description;
    private BigDecimal originalGrossPrice;
    private BigDecimal salesPrice;
    private String productClass;

}
