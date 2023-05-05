package org.aseguradora.aseguradora.mgr;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dao.ReaseguroDAO;
import org.aseguradora.aseguradora.dto.Reaseguro;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReaseguroMgr {
    private static ReaseguroMgr instance;
    private ReaseguroDAO reaseguroDAO;

    private ReaseguroMgr(){
        reaseguroDAO = ReaseguroDAO.getInstance();
    }

    public static ReaseguroMgr getInstance(){
        if (instance == null){
            instance = new ReaseguroMgr();
        }
        return instance;
    }

    public void SaveOrUpdate(Reaseguro reaseguradora, Conexion con) throws MgrException, SQLException {
        try{
            Reaseguro r = reaseguroDAO.ObgetByIde(reaseguradora.getNmid(),con);
            if (r==null) {
                reaseguroDAO.Insert(reaseguradora, con);
                System.out.println("Usuario Guardado Correctamente");
            }else{
                reaseguroDAO.Update(reaseguradora, con);
                System.out.println("Usuario Modificado Correctamente");
            }
        }catch (DaoExecption e){
            throw  new MgrException(e);
        }

    }

    public void delete(int nmid,Conexion con) throws MgrException{
        Reaseguro reaseguradora = null;
        try {
            reaseguradora = reaseguroDAO.ObgetByIde(nmid,con);
            if(reaseguradora == null){
                System.out.println("Error, no se encuentra el id");
            }else {
                reaseguroDAO.Delete(nmid,con);
            }
        }catch (DaoExecption e){
            throw new MgrException(e);
        } catch (Exception e){
            throw  new MgrException(e);

        }
    }

    public ArrayList<Reaseguro> getAll(Conexion con) throws MgrException, SQLException{
        ArrayList<Reaseguro> reaseguradoralista  = new ArrayList();

        try{
            reaseguradoralista = reaseguroDAO.getAll(con);


        }catch (DaoExecption e){
            throw new MgrException(e);
        }
        return reaseguradoralista;
    }
}
