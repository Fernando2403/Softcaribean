package org.aseguradora.aseguradora.mgr;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dao.SiniestroDAO;
import org.aseguradora.aseguradora.dto.Siniestro;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.exception.MgrException;

import java.sql.SQLException;
import java.util.ArrayList;
public class SiniestroMgr {
    private SiniestroDAO siniestroDAO;
    private static SiniestroMgr instance;

    public static SiniestroMgr getInstance(){
        if(instance == null){
            instance = new SiniestroMgr();
        }
        return instance;
    }

    private SiniestroMgr(){
        siniestroDAO = SiniestroDAO.getInstance();
    }

    public void save_update(Siniestro sin, Conexion con) throws MgrException {
        Siniestro siniestro = null;
        try{
            siniestro = siniestroDAO.getById(sin.getNmid(),con);
            if(siniestro == null){

                siniestroDAO.insert(sin, con);
                System.out.println("Usuario Guardado Correctamente");
            }else{
                siniestroDAO.update(sin,con);
                System.out.println("Usuario Modificado Correctamente");
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            ex.printStackTrace();
            throw  new MgrException(ex);
        }
    }

    public void delete(int nmid,Conexion con) throws MgrException {
        Siniestro siniestro = null;
        try {
            siniestro = siniestroDAO.getById(nmid,con);
            if(siniestro == null){
                System.out.println("Error, no encuentra el id");
            }else {
                siniestroDAO.delete(nmid,con);
            }
        }catch (DaoExecption ex){
            throw new MgrException(ex);
        } catch (Exception ex){
            throw new MgrException(ex);
        }
    }

    public ArrayList<Siniestro> getAll(Conexion con) throws MgrException, SQLException {
        ArrayList<Siniestro> sin = new ArrayList();
        try{
            sin = siniestroDAO.getAll(con);
        }catch (DaoExecption ex){
            throw new MgrException(ex);

        }
        return sin;
    }
}
