package org.aseguradora.aseguradora.mgr;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dao.SeguroDAO;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.exception.MgrException;

import org.aseguradora.aseguradora.dto.Seguro;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeguroMgr {
    private SeguroDAO seguroDAO;
    private static SeguroMgr instance;

    public static SeguroMgr getInstance(){
        if(instance == null){
            instance = new SeguroMgr();
        }
        return instance;
    }

    private SeguroMgr(){
        seguroDAO = SeguroDAO.getInstance();
    }

    public void save_update(Seguro seguro, Conexion con) throws MgrException {
        Seguro segu = null;
        try{
            segu = seguroDAO.getById(seguro.getNmid(),con);

            if(segu == null){
                seguroDAO.insert(seguro, con);
                System.out.println("Usuario Guardado Correctamente");
            }else{
                seguroDAO.update(seguro,con);
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
        Seguro seguro = null;
        try {
            seguro = seguroDAO.getById(nmid,con);
            if(seguro == null){
                System.out.println("Error, no encuentra el id");
            }else {
                seguroDAO.delete(nmid,con);
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            throw new MgrException(ex);
        }
    }


    public ArrayList<Seguro> getAll(Conexion con) throws MgrException, SQLException {
        ArrayList<Seguro> lista= new ArrayList();
        try{
            lista= seguroDAO.getAll(con);
        }catch (DaoExecption ex){
            throw new MgrException(ex);

        }
        return lista;
    }

}
