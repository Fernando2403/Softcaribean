package org.aseguradora.aseguradora.mgr;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dao.BeneficiarioDAO;
import org.aseguradora.aseguradora.dto.Beneficiario;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BeneficiarioMgr {

    private BeneficiarioDAO beneficiarioDAO;
    private static BeneficiarioMgr instance;

    public static BeneficiarioMgr getInstance(){
        if(instance == null){
            instance = new BeneficiarioMgr();
        }
        return instance;
    }

    private BeneficiarioMgr(){
        beneficiarioDAO = BeneficiarioDAO.getInstance();
    }

    public void save_update(Beneficiario beneficiario, Conexion con) throws MgrException {
        Beneficiario bene = null;
        try{
            bene = beneficiarioDAO.getById(beneficiario.getNmid(),con);

            if(bene == null){
                beneficiarioDAO.insert(beneficiario, con);
                System.out.println("Usuario Guardado Correctamente");
            }else{
                beneficiarioDAO.update(beneficiario,con);
                System.out.println("Usuario Modificado Correctamente");
            }
        }catch (DaoExecption ex){
            ex.printStackTrace();
            throw new MgrException(ex);
        } catch (Exception ex){
            throw  new MgrException(ex);
        }
    }

    public void delete(int nmid,Conexion con) throws MgrException {
        Beneficiario bene = null;
        try {
            bene = beneficiarioDAO.getById(nmid,con);
            if(bene == null){
                System.out.println("Error, no encuentra el id");
            }else {
                beneficiarioDAO.delete(nmid,con);
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            throw new MgrException(ex);
        }
    }


    public ArrayList<Beneficiario> getAll(Conexion con) throws MgrException, SQLException {
        ArrayList<Beneficiario> lista= new ArrayList();
        try{
            lista= beneficiarioDAO.getAll(con);
        }catch (DaoExecption ex){
            throw new MgrException(ex);

        }
        return lista;
    }

}
