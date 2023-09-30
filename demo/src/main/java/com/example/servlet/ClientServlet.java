package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servlet.model.Client;
import com.example.servlet.repository.ClientRepository;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    private ClientRepository clientRepository = new ClientRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        try {
            Client client = this.clientRepository.selectClient(name);

            response.getWriter().print(client);
        } catch (Exception e) {
            response.getWriter().println(e);
        }

    }
}
