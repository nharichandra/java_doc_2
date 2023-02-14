package com.nisum.poc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Addresses {

    @JsonProperty(value = "physicalOrigin")
    private Address physicalOrigin;

    @JsonProperty(value = "administrativeOrigin")
    private Address administrativeOrigin;

    @JsonProperty(value = "customerDestination")
    private Address customerDestination;

}

