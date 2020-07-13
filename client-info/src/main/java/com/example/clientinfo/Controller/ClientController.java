package com.example.clientinfo.Controller;


import com.example.clientinfo.Data.Client;
import com.example.clientinfo.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import java.util.List;

@RestController
@RequestMapping("/client")
@EnableEurekaClient
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public List findAll() {
        return clientService.findAll();
    }

    @GetMapping(path = "{id}")
    public Object findById(@PathVariable("id") String id){
        return clientService.findById(id);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody Client client) {
        Client profile = clientService.createClient(client);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profile);
    }

    @PutMapping(path = "edit/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Client client){
        Object update = clientService.updateClient(id,client);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(update);
    }

    @DeleteMapping(path = "{id}")
    public int deleteClient(@PathVariable("id") String id){
        return clientService.deleteClient(id);
    }

}
