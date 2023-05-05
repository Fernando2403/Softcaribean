package org.aseguradora.aseguradora.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion;

    private boolean ocupado = false;

    public Connection getConexion() {
        return conexion;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void open(){
        String url, user, password;
        url= "jdbc:postgresql://localhost:5432/aseguradora";
        user = "postgres";
        password = "1234";
        try {
             conexion = DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(false);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos "+ e.getMessage());
        }

    }

}
