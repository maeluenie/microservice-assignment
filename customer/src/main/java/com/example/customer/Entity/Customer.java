package com.example.customer.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String rmId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNo;

    private List<String> accounts;

}
