package org.aseguradora.aseguradora.mgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dao.PagosDAO;
import org.aseguradora.aseguradora.dto.Pagos;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;
public class PagosMgr {
    private static PagosMgr instance;

    private PagosDAO pagosDAO;
    private PagosMgr(){
        pagosDAO = PagosDAO.getInstance();
    }

    public static PagosMgr getInstance(){
        if(instance == null){
            instance = new PagosMgr();
        }
        return instance;
    }

    public void SaveUpdate(Pagos pagos, Conexion con) throws MgrException {
        Pagos p = null;
        try {
            p = pagosDAO.GetById(pagos.getNmid(),con);
            if (p == null){
                pagosDAO.Insert(pagos,con);
                System.out.println("Usuario Guardado Correctamente");
            }else{
                pagosDAO.Update(pagos,con);
                System.out.println("Usuario Modificado Correctamente");
            }
        }catch (DaoExecption ex){
            ex.printStackTrace();
            throw  new MgrException(ex);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new MgrException(ex);
        }
    }

    public void Delete (int nmid, Conexion con) throws MgrException{
        Pagos p = null;
        try {
            p = pagosDAO.GetById(nmid,con);
            if (p ==null){
                System.out.println("Error, no encuentra el id");

            }else {
                pagosDAO.Delete(nmid,con);
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        }catch (Exception ex){
            throw new MgrException(ex);
        }
    }

    public ArrayList<Pagos> GetAll(Conexion con) throws MgrException, SQLException {
        ArrayList<Pagos> pag= new ArrayList();
        try {
            pag = pagosDAO.GetAll(con);
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        }
        return pag;
    }

}
