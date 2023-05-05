package org.aseguradora.aseguradora.dao;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dto.Siniestro;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.DaoExecption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SiniestroDAO {
    private static SiniestroDAO instance;

    private SiniestroDAO(){

    }
    public static SiniestroDAO getInstance(){
        if(instance == null){
            instance = new SiniestroDAO();
        }
        return instance;
    }

    public void insert(Siniestro siniestro, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm= null;
        try {
            String Insert = "INSERT INTO public.siniestro(\n" +
                    "\t tipo_siniestro, f_siniestro, lugar, nmid_tomador)\n" +
                    "\tVALUES (?, ?, ?, ?);";
            ptsm = con.getConexion().prepareStatement(Insert);
            ptsm.setString(1, siniestro.getTipo_siniestro());
            java.sql.Date utilDate = new java.sql.Date(siniestro.getF_siniestro().getTime());
            ptsm.setDate(2, utilDate);
            ptsm.setString(3, siniestro.getLugar());
            ptsm.setInt(4, siniestro.getTo().getNmid());
            ptsm.executeUpdate();
        } catch (Exception ex){
            throw new DaoExecption(ex);
        }finally {
            ptsm.close();
        }


    }
    public void update(Siniestro siniestro, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {
            String Update = "UPDATE public.siniestro\n" +
                    "\tSET  tipo_siniestro=?, f_siniestro=?, lugar=?, nmid_tomador=? \n" +
                    "\tWHERE nmid=?;";
            ptsm = con.getConexion().prepareStatement(Update);
            ptsm.setString(1,siniestro.getTipo_siniestro());
            java.sql.Date utilDate = new java.sql.Date(siniestro.getF_siniestro().getTime());
            ptsm.setDate(2,utilDate);
            ptsm.setString(3,siniestro.getLugar());
            ptsm.setInt(4, siniestro.getTo().getNmid());
            ptsm.setInt(5,siniestro.getNmid());
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
            String delete = "DELETE FROM public.siniestro WHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(delete);
            ptsm.setInt(1,nmid);
            ptsm.executeUpdate();
        }catch (Exception ex){
            throw new DaoExecption(ex);
        } finally {
            ptsm.close();
        }
    }
    public Siniestro getById(int nmid, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        Siniestro resultsin = null;
        String SELECTBYID = "SELECT S.nmid, \n " +
                "S.tipo_siniestro,\n" +
                "\tS.f_siniestro, \n" +
                "\tS.lugar,\n" +
                "\tT.nmid AS id_tomador,\n" +
                "    T.documento,\n" +
                "    T.tipo_doc,\n" +
                "    T.nombre,\n" +
                "    T.apellido,\n" +
                "    T.direccion,\n" +
                "    T.telefono,\n" +
                "    T.ocupacion,\n" +
                "    T.correo,\n" +
                "    T.f_naci\n" +
                "    FROM siniestro S\n" +
                "    INNER JOIN tomador T\n" +
                "    ON S.nmid_tomador = T.nmid\n" +
                "\t WHERE S.nmid = ?";
        try {
            ptsm = con.getConexion().prepareStatement(SELECTBYID);
            ptsm.setInt(1,nmid);
            rs = ptsm.executeQuery();
            while (rs.next()){
                resultsin = new Siniestro();
                resultsin.setTipo_siniestro(rs.getString("tipo_siniestro"));
                resultsin.setF_siniestro(rs.getDate("f_siniestro"));
                resultsin.setLugar(rs.getString("lugar"));

                Tomador tomad = new Tomador();
                tomad.setNmid(rs.getInt("nmid"));
                tomad.setDocumento(rs.getString("documento"));
                tomad.setTipo_doc(rs.getString("tipo_doc"));
                tomad.setNombre(rs.getString("nombre"));
                tomad.setApellido(rs.getString("apellido"));
                tomad.setDireccion(rs.getString("direccion"));
                tomad.setTelefono(rs.getString("telefono"));
                tomad.setOcupacion(rs.getString("ocupacion"));
                tomad.setCorreo(rs.getString("correo"));
                tomad.setF_naci(rs.getDate("f_naci"));
                resultsin.setTo(tomad);
            }
        } catch (Exception ex){

            throw new DaoExecption(ex);
        } finally {

            rs.close();
            ptsm.close();

        }
        return resultsin;
    }
    public ArrayList<Siniestro> getAll(Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        ArrayList<Siniestro> list = new ArrayList();
        String SELECT = "SELECT \n" +
                "\tS.nmid, \n" +
                "\tS.tipo_siniestro,\n" +
                "\tS.f_siniestro, \n" +
                "\tS.lugar,\n" +
                "\tT.nmid AS id_tomador,\n" +
                "    \tT.documento,\n" +
                "    \tT.tipo_doc,\n" +
                "    \tT.nombre,\n" +
                "    \tT.apellido,\n" +
                "    \tT.direccion,\n" +
                "    \tT.telefono,\n" +
                "    \tT.ocupacion,\n" +
                "    \tT.correo,\n" +
                "    \tT.f_naci\n" +
                "FROM siniestro S\n" +
                "    INNER JOIN tomador T\n" +
                "    ON S.nmid_tomador = T.nmid;";
        try {
            ptsm = con.getConexion().prepareStatement(SELECT);
            rs= ptsm.executeQuery();
            while (rs.next()){
                Siniestro sin = new Siniestro();
                sin.setNmid(rs.getInt("nmid"));
                sin.setTipo_siniestro(rs.getString("tipo_siniestro"));
                sin.setF_siniestro(rs.getDate("f_siniestro"));
                sin.setLugar(rs.getString("lugar"));

                Tomador tomado = new Tomador();
                tomado.setNmid(rs.getInt("id_tomador"));
                tomado.setDocumento(rs.getString("documento"));
                tomado.setTipo_doc(rs.getString("tipo_doc"));
                tomado.setNombre(rs.getString("nombre"));
                tomado.setApellido(rs.getString("apellido"));
                tomado.setDireccion(rs.getString("direccion"));
                tomado.setTelefono(rs.getString("telefono"));
                tomado.setOcupacion(rs.getString("ocupacion"));
                tomado.setCorreo(rs.getString("correo"));
                tomado.setF_naci(rs.getDate("f_naci"));
                sin.setTo(tomado);
                list.add(sin);
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
