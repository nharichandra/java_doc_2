package com.nisum.poc.pojo;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreBean {

    private String storeId;
    private Map<String, Boolean> disabledFeatures;
}
