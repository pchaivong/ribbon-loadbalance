package com.oc.poc.ribbonloadbalance.service;

import com.oc.poc.ribbonloadbalance.dto.DoSomethingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pchaivong on 11/6/2017 AD.
 */
@Service
public class ExternalService {

    private RestTemplate restTemplate;

    @Autowired
    public ExternalService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public DoSomethingResponse doSomething(){
        return restTemplate.getForObject("http://external-api/dosomething", DoSomethingResponse.class);
    }
}
