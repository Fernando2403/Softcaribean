package org.aseguradora.aseguradora.dao;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.DaoExecption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TomadorDAO {
    private static TomadorDAO instance;

    private TomadorDAO(){

    }
    public static TomadorDAO getInstance(){
        if(instance == null){
            instance = new TomadorDAO();
        }
        return instance;
    }
    public void insert(Tomador tomador, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm= null;
     try {
          String Insert = "INSERT INTO public.tomador(\n" +
                  "\t documento, tipo_doc, nombre, apellido, direccion, telefono, ocupacion, correo, f_naci)\n" +
                  "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
          ptsm = con.getConexion().prepareStatement(Insert);
          ptsm.setString(1,tomador.getDocumento());
          ptsm.setString(2,tomador.getTipo_doc());
          ptsm.setString(3,tomador.getNombre());
          ptsm.setString(4,tomador.getApellido());
          ptsm.setString(5,tomador.getDireccion());
          ptsm.setString(6,tomador.getTelefono());
          ptsm.setString(7,tomador.getOcupacion());
          ptsm.setString(8,tomador.getCorreo());
          java.sql.Date utilDate = new java.sql.Date(tomador.getF_naci().getTime());
          ptsm.setDate(9,utilDate);
          ptsm.executeUpdate();
         } catch (Exception ex){
        throw new DaoExecption(ex);
      }finally {
         ptsm.close();
     }


    }
    public void update(Tomador tomador, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {
            String Update = "UPDATE public.tomador SET documento=?, tipo_doc=?, nombre=?, apellido=?, direccion=?, telefono=?, ocupacion=?, correo=?, f_naci=? WHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(Update);
            ptsm.setString(1, tomador.getDocumento());
            ptsm.setString(2, tomador.getTipo_doc());
            ptsm.setString(3, tomador.getNombre());
            ptsm.setString(4, tomador.getApellido());
            ptsm.setString(5, tomador.getDireccion());
            ptsm.setString(6, tomador.getTelefono());
            ptsm.setString(7, tomador.getOcupacion());
            ptsm.setString(8, tomador.getCorreo());
            java.sql.Date utilDate = new java.sql.Date(tomador.getF_naci().getTime());
            ptsm.setDate(9, utilDate);
            ptsm.setInt(10, tomador.getNmid());
            ptsm.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoExecption(ex);
        } finally {
            if (ptsm != null) {
                ptsm.close();
            }
        }
    }


    public void delete(int nmid,Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try{
           String delete = "DELETE FROM public.tomador WHERE nmid=?";
           ptsm = con.getConexion().prepareStatement(delete);
           ptsm.setInt(1,nmid);
           ptsm.executeUpdate();
        }catch (Exception ex){
            throw new DaoExecption(ex);
        } finally {
            ptsm.close();
        }
    }

    public Tomador getById(int nmid,Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        Tomador resulttom = null;
        String SELECTBYID = "SELECT documento,tipo_doc,nombre,apellido,direccion,telefono,ocupacion,correo,f_naci FROM public.tomador WHERE nmid = ?";

        try {
            ptsm = con.getConexion().prepareStatement(SELECTBYID);
            ptsm.setInt(1,nmid);
            rs = ptsm.executeQuery();
             while (rs.next()){
                 resulttom = new Tomador();
                 resulttom.setDocumento(rs.getString("documento"));
                 resulttom.setTipo_doc(rs.getString("tipo_doc"));
                 resulttom.setNombre(rs.getString("nombre"));
                 resulttom.setApellido(rs.getString("apellido"));
                 resulttom.setDireccion(rs.getString("direccion"));
                 resulttom.setTelefono(rs.getString("telefono"));
                 resulttom.setCorreo(rs.getString("correo"));
                 resulttom.setF_naci(rs.getDate("f_naci"));
             }
        } catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        } finally {
            ptsm.close();
            rs.close();
        }
     return resulttom;
    }

   public ArrayList<Tomador> getAll(Conexion con) throws DaoExecption, SQLException {
       PreparedStatement ptsm = null;
       ResultSet rs = null;
       ArrayList<Tomador> list = new ArrayList();
       String SELECT = "SELECT nmid,documento,tipo_doc,nombre,apellido,direccion,telefono,ocupacion,correo,f_naci FROM public.tomador";
       try {
           ptsm = con.getConexion().prepareStatement(SELECT);
           rs= ptsm.executeQuery();
           while (rs.next()){
               Tomador tom = new Tomador();
               tom.setNmid(rs.getInt("nmid"));
               tom.setDocumento(rs.getString("documento"));
               tom.setTipo_doc(rs.getString("tipo_doc"));
               tom.setNombre(rs.getString("nombre"));
               tom.setApellido(rs.getString("apellido"));
               tom.setDireccion(rs.getString("direccion"));
               tom.setTelefono(rs.getString("telefono"));
               tom.setOcupacion(rs.getString("ocupacion"));
               tom.setCorreo(rs.getString("correo"));
               tom.setF_naci(rs.getDate("f_naci"));
               list.add(tom);
           }
       } catch (Exception ex){

           throw new DaoExecption(ex);
       } finally {
           rs.close();
           ptsm.close();

       }
       return list;
   }


}
