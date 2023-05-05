package org.aseguradora.aseguradora.services;

import org.aseguradora.aseguradora.exception.MgrException;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mgr.SeguroMgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.aseguradora.aseguradora.dto.Seguro;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeguroServices {
    private SeguroMgr seguroMgr;
    private static SeguroServices instance;

    private SeguroServices(){
        seguroMgr = SeguroMgr.getInstance();
    }

    public static SeguroServices getInstance(){
        if(instance == null){
            instance = new SeguroServices();
        }
        return instance;
    }

    public void create(Seguro seguro) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try {
            seguroMgr.save_update(seguro, con);
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
            seguroMgr.delete(nmid, con);
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

    public ArrayList<Seguro> getAll() throws ServicesException, SQLException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        ArrayList<Seguro> lista= new ArrayList();
        try{
            lista=seguroMgr.getAll(con);
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
