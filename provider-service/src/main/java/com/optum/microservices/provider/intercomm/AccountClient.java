package com.optum.microservices.provider.intercomm;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.optum.microservices.provider.model.Account;

@FeignClient("claim-service")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/claim/provider/{customerId}")
    List<Account> getAccounts(@PathVariable("customerId") Integer customerId);
    
}
