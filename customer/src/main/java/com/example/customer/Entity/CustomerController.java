package com.example.customer.Entity;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @HystrixCommand(fallbackMethod = "listAccountFallback")
    @GetMapping("/{id}/accounts")
    public Map<String, Object> listAccount(@PathVariable String id){
        Map<String, Object> result = new HashMap<>();
        List<Object> accounts = new ArrayList<>();
        Customer customer = customerRepository.findByRmId(id);
        if(customer != null){
            for(String accountNo : customer.getAccounts()){
                LOGGER.info("call to account-service with accountNo: {}", accountNo);
                Map<String, Object> accountInfo = restTemplate.getForObject("http://account-service/accounts/" + accountNo, Map.class);
                LOGGER.info("Got response from account-service");
                accounts.add(accountInfo);
            }
        }
        result.put(id, accounts);
        return result;
    }

    public Map<String, Object> listAccountFallback(String id, Throwable hystrixCommand){
        Map<String, Object> result = new HashMap<>();
        result.put("success", "false");
        result.put("message", hystrixCommand.getMessage());
        return result;
    }


}
