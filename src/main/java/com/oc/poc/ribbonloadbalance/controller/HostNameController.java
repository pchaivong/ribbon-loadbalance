package com.oc.poc.ribbonloadbalance.controller;

import com.netflix.client.config.IClientConfig;
import com.oc.poc.ribbonloadbalance.dto.DelayedRequest;
import com.oc.poc.ribbonloadbalance.dto.DelayedResponse;
import com.oc.poc.ribbonloadbalance.dto.DoSomethingResponse;
import com.oc.poc.ribbonloadbalance.dto.HostnameResponse;
import com.oc.poc.ribbonloadbalance.service.ExternalService;
import com.oc.poc.ribbonloadbalance.service.HostNameService;
import com.oc.poc.ribbonloadbalance.service.HostnameDelayedResponse;
import com.oc.poc.ribbonloadbalance.service.HostnameServiceResponse;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pchaivong on 11/3/2017 AD.
 */

@RestController
@Slf4j
public class HostNameController {

    private final HostNameService hostNameService;
    private final ExternalService externalService;

    public HostNameController(HostNameService hostNameService,
                              ExternalService externalService){
        this.hostNameService = hostNameService;
        this.externalService = externalService;
    }

    @RequestMapping(value = "/host", method = RequestMethod.GET)
    public HostnameResponse getHostname(){
        long start = System.currentTimeMillis();
        HostnameServiceResponse resp = hostNameService.getHostname();
        long elapsed = System.currentTimeMillis() - start;

        HostnameResponse response = new HostnameResponse();
        response.setHostname(resp.getHostname());
        response.setElapsedTime(elapsed);

        log.info("Get hostname elapsed time: " + elapsed);


        return response;
    }


    // Delayed
    @RequestMapping(value = "/delayed", method = RequestMethod.GET)
    public DelayedResponse getDelayedConfiguration(){
        HostnameDelayedResponse resp = hostNameService.getDelay();

        DelayedResponse response = new DelayedResponse();
        response.setHostname(resp.getHostname());
        response.setDelayed(resp.getDelayed());

        return response;
    }


    @RequestMapping(value = "/delayed", method = RequestMethod.PUT)
    public DelayedResponse updateDelayedConfiguration(@RequestBody DelayedRequest request){

        HostnameDelayedResponse resp = hostNameService.setDelay(request.getDelayed());
        DelayedResponse response = new DelayedResponse();
        response.setHostname(resp.getHostname());
        response.setDelayed(resp.getDelayed());
        return response;
    }

    @RequestMapping(value = "/external", method = RequestMethod.GET)
    public DoSomethingResponse externalAPI(){
        return externalService.doSomething();
    }
}
