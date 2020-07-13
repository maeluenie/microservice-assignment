package com.example.clientinfo.Service;


import com.example.clientinfo.Data.Client;
import com.example.clientinfo.Repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List findAll() {
        log.info("invoke service findAll");
        return clientRepository.findAll();
    }

    public Object findById(String id) {
        log.info("invoke service findById");
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        log.info("invoke service createClient");
        return clientRepository.createClient(client);
    }

//    public Object updateClient(String id, Client client) throws Exception {
//        log.info("invoke service updateClient");
//        if (clientRepository.updateClient(id, client) == 1) {
//            // find client data.
//            return clientRepository.findById(id);
//        }
//        throw new Exception("Can't update data");
//    }

    public Object updateClient(String id, Client client)  {
        log.info("invoke service updateClient");
        return clientRepository.updateClient(id, client);
    }


    public int deleteClient(String id) {
        log.info("invoke service deleteClient: " + id);
        return clientRepository.deleteClient(id);
    }


}
