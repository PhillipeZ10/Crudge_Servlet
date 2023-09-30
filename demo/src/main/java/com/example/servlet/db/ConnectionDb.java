package com.example.servlet.db;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionDb {
    private static String banco = "jdbc:mysql://localhost:8889/teste";
    private static String usuario = "root";
    private static String senha = "root";
    private static Connection conn = null;

    public static Connection getConnection() {
        return conn;
    }

    static {
        conectar();
    }

    private static void conectar() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(banco, usuario, senha);
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
