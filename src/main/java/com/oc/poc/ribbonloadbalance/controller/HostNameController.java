package com.oc.poc.ribbonloadbalance.controller;

import com.oc.poc.ribbonloadbalance.dto.DelayedRequest;
import com.oc.poc.ribbonloadbalance.dto.DelayedResponse;
import com.oc.poc.ribbonloadbalance.dto.HostnameResponse;
import com.oc.poc.ribbonloadbalance.service.HostNameService;
import com.oc.poc.ribbonloadbalance.service.HostnameDelayedResponse;
import com.oc.poc.ribbonloadbalance.service.HostnameServiceResponse;
import com.oc.poc.ribbonloadbalance.service.HostnameSetDelayedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pchaivong on 11/3/2017 AD.
 */

@RestController
public class HostNameController {


    private final HostNameService hostNameService;

    public HostNameController(HostNameService hostNameService){
        this.hostNameService = hostNameService;
    }

    @RequestMapping(value = "/host", method = RequestMethod.GET)
    public HostnameResponse getHostname(){
        long start = System.currentTimeMillis();
        HostnameServiceResponse resp = hostNameService.getHostname();
        long elapsed = System.currentTimeMillis() - start;

        HostnameResponse response = new HostnameResponse();
        response.setHostname(resp.getHostname());
        response.setElapsedTime(elapsed);

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
}
