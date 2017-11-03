package com.oc.poc.ribbonloadbalance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pchaivong on 11/3/2017 AD.
 */

@Service
public class HostNameService {


    private RestTemplate restTemplate;

    @Autowired
    public HostNameService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public HostnameServiceResponse getHostname(){
        return restTemplate.getForObject("http://simple-hostname/hostname", HostnameServiceResponse.class);
    }

    public HostnameDelayedResponse setDelay(long delayed){
        HostnameSetDelayedRequest delayedRequest = new HostnameSetDelayedRequest();
        delayedRequest.setDelayed(delayed);
        return restTemplate.postForObject("http://simple-hostname/delayed", delayedRequest, HostnameDelayedResponse.class);
    }

    public HostnameDelayedResponse getDelay(){
        return restTemplate.getForObject("http://simple-hostname/delayed", HostnameDelayedResponse.class);
    }
}
