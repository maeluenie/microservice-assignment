package com.example.customer.Entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class CustomerRepository {

    private List<Customer> customers;

    public CustomerRepository(){
        initialize();
    }

    private void initialize(){
        List<String> accounts = new ArrayList<>();
        customers = new ArrayList<>();
        Customer customer = new Customer("00001","firstName","lastName",
                "Bangkok","02020202020", accounts);
        accounts.add("1000000000001");
        accounts.add("1000000000002");
        accounts.add("1000000000003");
        accounts.add("1000000000004");
        customers.add(customer);
    }

    public Customer findByRmId(String id){
        for(Customer customer : customers){
            if(customer.getRmId().equals(id)){
                return customer;
            }
        }
        return null;
    }

}
