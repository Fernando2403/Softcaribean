package org.aseguradora.aseguradora.services;
import org.aseguradora.aseguradora.mgr.PagosMgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.aseguradora.aseguradora.dto.Pagos;
import org.aseguradora.aseguradora.exception.MgrException;
import org.aseguradora.aseguradora.exception.ServicesException;

import java.sql.SQLException;
import java.util.ArrayList;

public class PagosServices {

    private PagosMgr pagosMgr;
    private static PagosServices instance;

    public PagosServices() {
        pagosMgr = pagosMgr.getInstance();
    }

    public static PagosServices getInstance(){
        if (instance == null){
            instance = new PagosServices();
        }
        return instance;
    }

    public void CreatePagos(Pagos pagos) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try {
            pagosMgr.SaveUpdate(pagos, con);
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
            pagosMgr.Delete(nmid, con);
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

    public ArrayList<Pagos> getAll() throws ServicesException, SQLException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        ArrayList<Pagos> pago = new ArrayList();
        try{
            pago= pagosMgr.GetAll(con);
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
        return pago;
    }
}

