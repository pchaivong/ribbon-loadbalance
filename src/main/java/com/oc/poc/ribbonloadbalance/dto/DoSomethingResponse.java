package com.oc.poc.ribbonloadbalance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by pchaivong on 11/6/2017 AD.
 */

@NoArgsConstructor
@Getter
@Setter
public class DoSomethingResponse {

    @JsonProperty("status")
    private String status;
}
