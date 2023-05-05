package org.aseguradora.aseguradora.services;

import org.aseguradora.aseguradora.exception.MgrException;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mgr.SiniestroMgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.aseguradora.aseguradora.dto.Siniestro;


import java.sql.SQLException;
import java.util.ArrayList;

public class SiniestroServices {
    private SiniestroMgr siniestroMgr;
    private static SiniestroServices instance;

    private SiniestroServices(){
        siniestroMgr = SiniestroMgr.getInstance();
    }

    public static SiniestroServices getInstance(){
        if(instance == null){
            instance = new SiniestroServices();
        }
        return instance;
    }

    public void create(Siniestro siniestro) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try {
            siniestroMgr.save_update(siniestro, con);
            managerConexion.commit(con);
        }catch (MgrException ex) {
            ex.printStackTrace();
            managerConexion.rollback(con);
            throw  new ServicesException(ex);
        }catch (SQLException sq){
            managerConexion.rollback(con);
            throw new ServicesException(sq);
        }finally {
            managerConexion.close(con);
        }
    }

    public void delete(int nmid) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try{
            siniestroMgr.delete(nmid, con);
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

    public ArrayList<Siniestro> getAll() throws ServicesException, SQLException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        ArrayList<Siniestro> lista= new ArrayList();
        try{
            lista = siniestroMgr.getAll(con);
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
