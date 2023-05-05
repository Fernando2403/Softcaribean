package org.aseguradora.aseguradora.services;

import org.aseguradora.aseguradora.mgr.BeneficiarioMgr;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.aseguradora.aseguradora.dto.Beneficiario;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BeneficiarioServices {

    private BeneficiarioMgr beneficiarioMgr;
    private static BeneficiarioServices instance;

    private BeneficiarioServices(){
        beneficiarioMgr = BeneficiarioMgr.getInstance();
    }

    public static BeneficiarioServices getInstance(){
        if(instance == null){
            instance = new BeneficiarioServices();
        }
        return instance;
    }

    public void create(Beneficiario beneficiario) throws ServicesException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        try {
            beneficiarioMgr.save_update(beneficiario, con);
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
            beneficiarioMgr.delete(nmid, con);
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

    public ArrayList<Beneficiario> getAll() throws ServicesException, SQLException {
        ManagerConexion managerConexion = ManagerConexion.getInstance();
        Conexion con = managerConexion.getConexion();
        ArrayList<Beneficiario> lista= new ArrayList();
        try{
            lista=beneficiarioMgr.getAll(con);
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
