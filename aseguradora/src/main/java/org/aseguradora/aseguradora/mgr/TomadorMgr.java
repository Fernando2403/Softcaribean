package org.aseguradora.aseguradora.mgr;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dao.TomadorDAO;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;

public class TomadorMgr {
    private TomadorDAO tomadorDAO;
    private static TomadorMgr instance;

    public static TomadorMgr getInstance(){
        if(instance == null){
            instance = new TomadorMgr();
        }
        return instance;
    }

    private TomadorMgr(){
       tomadorDAO = TomadorDAO.getInstance();
    }

    public void save_update(Tomador tom, Conexion con) throws MgrException {
        Tomador tomador = null;
        try{
            tomador = tomadorDAO.getById(tom.getNmid(),con);

            if(tomador == null){
                tomadorDAO.insert(tom, con);
                System.out.println("Usuario Guardado Correctamente");
            }else{
                tomadorDAO.update(tom,con);
                System.out.println("Usuario Modificado Correctamente");
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            throw  new MgrException(ex);
        }
    }

    public void delete(int nmid,Conexion con) throws MgrException{
        Tomador tomador = null;
        try {
            tomador = tomadorDAO.getById(nmid,con);
            if(tomador == null){
                System.out.println("Error, no encuentra el id");
            }else {
                tomadorDAO.delete(nmid,con);
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            throw new MgrException(ex);
        }
    }
   /* public void getById(int nmid,Conexion con) throws MgrException{
        Tomador tomador = null;
        try {
            tomador = tomadorDAO.getById(nmid,con);
            if(tomador == null){
                System.out.println("Error, no encuentra el id");
            }else {
                tomadorDAO.delete(nmid,con);
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            throw new MgrException(ex);
        }
    }*/

    public ArrayList<Tomador> getAll(Conexion con) throws MgrException, SQLException {
          ArrayList<Tomador> lista= new ArrayList();
        try{
              lista= tomadorDAO.getAll(con);
          }catch (DaoExecption ex){
          throw new MgrException(ex);

          }
        return lista;
    }

}
