package com.example.clientinfo.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Client {

    private String id;
    private String email;
    private String name;
    private String password;
    private String role;
}
