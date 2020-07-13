package com.example.clientinfo.Repository.Mapper;

import com.example.clientinfo.Data.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException{
        Client client = new Client();
        client.setId(resultSet.getString("client_id"));
        client.setEmail(resultSet.getString("email"));
        client.setName(resultSet.getString("name"));
        client.setPassword(resultSet.getString("password"));
        client.setRole(resultSet.getString("role"));

        return client;
    }
}
