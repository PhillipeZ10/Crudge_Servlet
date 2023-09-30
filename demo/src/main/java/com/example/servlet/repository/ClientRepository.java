package com.example.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.servlet.db.ConnectionDb;
import com.example.servlet.model.Client;

public class ClientRepository {
    private Connection conn;

    public ClientRepository() {
        conn = ConnectionDb.getConnection();
    }

    public Client save(Client client) throws Exception {
        if (client.ehNovo()) {
            String sql = "INSERT INTO cliente(nome, endereco, modalidade) VALUES(?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, client.getNome());
            stmt.setString(2, client.getEndereco());
            stmt.setString(3, client.getModalidade());

            stmt.execute();

            conn.commit();
        } else {
            String sql = "UPDATE cliente SET nome = ?, endereco =?, modalidade = ? WHERE id = " + client.getId() + ";";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, client.getNome());
            stmt.setString(2, client.getEndereco());
            stmt.setString(3, client.getModalidade());

            stmt.executeUpdate();

            conn.commit();
        }
        return this.selectClient(client.getNome());
    }

    public Client selectClient(String name) throws Exception {
        Client client = new Client();

        String sql = "SELECT * FROM cliente WHERE nome = '" + name + "'";

        PreparedStatement stmt = conn.prepareStatement(sql);
        System.out.println(stmt);
        
        ResultSet rst = stmt.executeQuery();

        while (rst.next()) {
            client.setId(rst.getLong("id"));
            client.setNome(rst.getString("nome"));
            client.setEndereco(rst.getString("endereco"));
            client.setModalidade(rst.getString("modalidade"));
        }

        return client;
    }
}
