package org.aseguradora.aseguradora.services;

import org.aseguradora.aseguradora.mgr.TomadorMgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;

public class TomadorServices {
    private TomadorMgr tomadorMgr;
    private static TomadorServices instance;

    private TomadorServices(){
        tomadorMgr = TomadorMgr.getInstance();
    }

    public static TomadorServices getInstance(){
        if(instance == null){
            instance = new TomadorServices();
        }
        return instance;
    }

    public void create(Tomador tomador) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try {
            tomadorMgr.save_update(tomador, con);
            managerConexion.commit(con);
        }catch (MgrException ex) {
            managerConexion.rollback(con);
            throw  new ServicesException(ex);
        }catch (SQLException sq){
            managerConexion.rollback(con);
            throw new ServicesException(sq);
        }finally {
            managerConexion.close(con);
        }
    }

    public void delete(int nmid) throws ServicesException{
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try{
            tomadorMgr.delete(nmid, con);
            managerConexion.commit(con);
        }catch (MgrException ex){
            managerConexion.rollback(con);
            throw  new ServicesException(ex);
        }catch (SQLException sq){
            managerConexion.rollback(con);
            throw new ServicesException(sq);
        }finally {
            managerConexion.close(con);
        }

    }

    public ArrayList<Tomador> getAll() throws ServicesException, SQLException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        ArrayList<Tomador> lista= new ArrayList();
        try{
            lista=tomadorMgr.getAll(con);
            managerConexion.commit(con);
        }catch (MgrException ex){
            managerConexion.rollback(con);
            throw new ServicesException(ex);
        }catch (SQLException sq){
            managerConexion.rollback(con);
            throw new ServicesException(sq);
        }finally {
            managerConexion.close(con);
        }
        return lista;
    }

}
