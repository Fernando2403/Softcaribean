package org.aseguradora.aseguradora.services;

import org.aseguradora.aseguradora.exception.MgrException;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mgr.ReaseguroMgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.aseguradora.aseguradora.dto.Reaseguro;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReaseguroServices {
    private static ReaseguroServices instance;

    private ReaseguroMgr reaseguradoramgr;

    private ReaseguroServices(){
        reaseguradoramgr = ReaseguroMgr.getInstance();

    }

    public static ReaseguroServices getInstance(){
        if (instance == null){
            instance = new ReaseguroServices();
        }
        return instance;
    }

    public void CrearReaseguradora(Reaseguro reaseguradora) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion conexion = managerConexion.getConexion();
        try {

            reaseguradoramgr.SaveOrUpdate(reaseguradora, conexion );
            managerConexion.Commit(conexion);

        } catch (MgrException e) {
            managerConexion.rollback(conexion);
            throw new ServicesException(e);
        } catch (SQLException se) {
            managerConexion.rollback(conexion);
            throw new ServicesException(se);
        }finally {
            managerConexion.close(conexion);
        }
    }


    public void delete(int nmid) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try{
            reaseguradoramgr.delete(nmid, con);
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

    public ArrayList<Reaseguro> VerReaseguradora() throws  ServicesException{
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion conexion = managerConexion.getConexion();
        ArrayList<Reaseguro> reaseguradoraLista = new ArrayList();
        try{
            reaseguradoraLista = reaseguradoramgr.getAll(conexion);
            managerConexion.Commit(conexion);
        }catch (MgrException e){
            managerConexion.rollback(conexion);
            throw new ServicesException(e);
        }catch (SQLException se){
            managerConexion.rollback(conexion);
            throw new ServicesException(se);
        }finally {
            managerConexion.close(conexion);
        }
        return reaseguradoraLista;



    }
}
