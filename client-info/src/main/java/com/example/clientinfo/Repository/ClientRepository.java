package com.example.clientinfo.Repository;

import com.example.clientinfo.Data.Client;
import com.example.clientinfo.Repository.Mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List findAll() {
        log.info("invoke repository findALl");
        return jdbcTemplate.query("SELECT * FROM client", new ClientMapper());
    }

    public Object findById(String id) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT * FROM client WHERE client_id = :client_id;");

        HashMap<String, Object> params = new HashMap<>();
        params.put("client_id", id);
        log.info("invoke repository findById");
        try {
            return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new ClientMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    public Client createClient(Client client) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE INTO client")
                .add("(client_id, email, name, password, role)")
                .add("VALUES (:client_id ,:email, :name, :password ,:role );");

        HashMap<String, Object> params = new HashMap<>();
        params.put("client_id", client.getId());
        params.put("email", client.getEmail());
        params.put("name", client.getName());
        params.put("password", client.getPassword());
        params.put("role", client.getRole());

        log.info("invoke repository create");
        try {
            namedParameterJdbcTemplate.update(sql.toString(), params);
            return client;
        } catch (Exception ex) {
            log.error("SQL error:" + ex.toString());
            return null;
        }

    }

    public Integer updateClient(String id ,Client client){
        StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE client")
                .add("SET email = :email, name = :name, password = :password, role = :role")
                .add("WHERE client_id = :client_id");

        log.info("invoke repository updateClient");
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", id);
        params.put("email", client.getEmail());
        params.put("name", client.getName());
        params.put("password", client.getPassword());
        params.put("role", client.getRole());
        log.info("SQL:"+ sql.toString());

        try {
            log.info("updated client");
            client.setId(id);
            return namedParameterJdbcTemplate.update(sql.toString(), params);
        } catch (Exception ex) {

            log.error("sql error" + ex.toString());
            return null;
        }

    }

    public int deleteClient(String id) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("DELETE FROM client WHERE client_id = :client_id");

        Map<String, Object> params = new HashMap<>();
        params.put("client_id", id);

        log.info("invoke repository deleteClient");
        if (namedParameterJdbcTemplate.update(sql.toString(), params) != 0) {
            log.info("1");
            return 1;
        } else {
            log.info("0");
            return 0;
        }
    }

}
