package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servlet.model.Client;
import com.example.servlet.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/MeuServlet")
public class MeuServlet extends HttpServlet {

    private ClientRepository clientRepository = new ClientRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println(name);

        try {
            Client client = this.clientRepository.selectClient(name);

            String clientJson = new ObjectMapper().writeValueAsString(client);
            response.getWriter().print(clientJson);
        } catch (Exception e) {
            response.getWriter().println(e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");

        Client newClient = new Client();
        newClient.setNome(name);
        newClient.setEndereco(endereco);
        newClient.setModalidade(modalidade);

        try {
            Client client = this.clientRepository.save(newClient);

            String clientJson = new ObjectMapper().writeValueAsString(client);
            response.getWriter().print(clientJson);
        } catch (Exception e) {
            response.getWriter().println(e);
        }

    }
}
