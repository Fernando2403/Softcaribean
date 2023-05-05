package org.aseguradora.aseguradora.bd;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerConexion {
    private static  ManagerConexion instance;
    ArrayList<Conexion> conexions;
    private ManagerConexion(){
        conexions = new ArrayList();
    }

    public static  ManagerConexion getInstance(){
        if(instance == null){
            instance = new ManagerConexion();
        }
        return instance;
    }

    public void start(){
        for (int i=0; i<10; i++){
            Conexion con = new Conexion();
            con.open();
            conexions.add(con);
        }
    }

    public Conexion getConexion(){
        Conexion confind = new Conexion();
        for (int i =0; i<10; i++){
            if(conexions.get(i).getOcupado() == false){
                confind = conexions.get(i);
                confind.setOcupado(true);
                break;
            }
        }
        return confind;
    }

    public void close(Conexion conex){
        conex.setOcupado(false);
    }

    public void commit(Conexion con) throws SQLException {
        try{
            con.getConexion().commit();
        }catch (SQLException sq){
            sq.printStackTrace();
        }

    }

    public void rollback(Conexion con) {
        try{
            con.getConexion().rollback();
        }catch (SQLException sq){
            sq.printStackTrace();
        }
    }

    public void Commit(Conexion conexion) {

    }
}
