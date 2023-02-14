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
public class TaxDetails {

   private String key;
   private BigDecimal value;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private BigDecimal previous;

   public TaxDetails(String taxName, BigDecimal taxAmount) {
      this.key = taxName;
      this.value = taxAmount;
   }

}

