package org.aseguradora.aseguradora.dao;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dto.Reaseguro;
import org.aseguradora.aseguradora.dto.Seguro;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.DaoExecption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SeguroDAO {
    private static SeguroDAO instance;
    private TomadorDAO tomadorDAO;
    private ReaseguroDAO reaseguroDAO;
    private SeguroDAO(){

    }
    public static SeguroDAO getInstance(){
        if(instance == null){
            instance = new SeguroDAO();
        }
        return instance;
    }
    public void insert(Seguro seguro, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm= null;
        String Insert = "INSERT INTO seguro(\n" +
                " tipo_seguro,valor,descripcion,f_inicial, f_final,nmid_tomador, nmid_reaseguradora)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            ptsm = con.getConexion().prepareStatement(Insert);
            ptsm.setString(1,seguro.getTipo_seguro());
            ptsm.setDouble(2,seguro.getValor());
            ptsm.setString(3,seguro.getDescripcion());
            java.sql.Date utilDate = new java.sql.Date(seguro.getF_inicial().getTime());
            ptsm.setDate(4,utilDate);
            java.sql.Date utilDatee = new java.sql.Date(seguro.getF_final().getTime());
            ptsm.setDate(5,utilDatee);
            ptsm.setInt(6,seguro.getTom().getNmid());
            ptsm.setInt(7,seguro.getRe().getNmid());
            ptsm.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        }finally {
            ptsm.close();
        }


    }
    public void update(Seguro seguro, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {

            String Update = "UPDATE public.seguro SET  tipo_seguro=?,valor=?,descripcion=?,f_inicial=?, f_final=?,nmid_tomador=?, nmid_reaseguradora=? WHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(Update);
            ptsm.setString(1,seguro.getTipo_seguro());
            ptsm.setDouble(2,seguro.getValor());
            ptsm.setString(3,seguro.getDescripcion());
            java.sql.Date utilDate = new java.sql.Date(seguro.getF_inicial().getTime());
            ptsm.setDate(4,utilDate);
            java.sql.Date utilDatee = new java.sql.Date(seguro.getF_final().getTime());
            ptsm.setDate(5,utilDatee);
            ptsm.setInt(6,seguro.getTom().getNmid());
            ptsm.setInt(7,seguro.getRe().getNmid());
            ptsm.setInt(8,seguro.getNmid());
            ptsm.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoExecption(ex);
        } finally {
            if (ptsm != null) {
                ptsm.close();
            }
        }
    }
    public Seguro getById(int nmid,Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        Seguro resultben = null;
        String SELECTBYID = "SELECT " +
                "\t S.tipo_seguro,\n" +
                "\t S.valor,\n" +
                "\t S.descripcion,\n" +
                "\t S.f_inicial,\n" +
                "\t S.f_final,\n" +
                "\t T.nmid AS id_tomador,\n" +
                "    T.documento,\n" +
                "    T.tipo_doc,\n" +
                "    T.nombre,\n" +
                "    T.apellido,\n" +
                "    T.direccion,\n" +
                "    T.telefono,\n" +
                "    T.ocupacion,\n" +
                "    T.correo,\n" +
                "    T.f_naci,\n" +
                "\t R.nmid AS id_reaseguradora,\n" +
                "\t R.nit,\n" +
                "\t R.razon_social,\n" +
                "\t R.monto_seguro,\n" +
                "\t R.porcentaje_cober\n" +
                "\t FROM seguro S\n" +
                "\t INNER JOIN tomador T \n" +
                "\t ON S.nmid_tomador = T.nmid \n" +
                "\t INNER JOIN reaseguradora R \n" +
                "\t ON S.nmid_reaseguradora = R.nmid \n" +
                "\t WHERE S.nmid = ?";
        try {
            ptsm = con.getConexion().prepareStatement(SELECTBYID);
            ptsm.setInt(1,nmid);
            rs = ptsm.executeQuery();
            while (rs.next()){
                resultben = new Seguro();
                resultben.setTipo_seguro(rs.getString("tipo_seguro"));
                resultben.setValor(rs.getDouble("valor"));
                resultben.setDescripcion(rs.getString("descripcion"));
                resultben.setF_inicial(rs.getDate("f_inicial"));
                resultben.setF_final(rs.getDate("f_final"));
                resultben.setNmid(nmid);
                Tomador tomador = new Tomador();
                tomador.setDocumento(rs.getString("documento"));
                tomador.setTipo_doc(rs.getString("tipo_doc"));
                tomador.setNombre(rs.getString("nombre"));
                tomador.setApellido(rs.getString("apellido"));
                tomador.setDireccion(rs.getString("direccion"));
                tomador.setTelefono(rs.getString("telefono"));
                tomador.setOcupacion(rs.getString("ocupacion"));
                tomador.setCorreo(rs.getString("correo"));
                tomador.setF_naci(rs.getDate("f_naci"));
                tomador.setNmid(rs.getInt("id_tomador"));
                resultben.setTom(tomador);
                Reaseguro reaseguro = new Reaseguro();
                reaseguro.setNit(rs.getString("nit"));
                reaseguro.setRazon_social(rs.getString("razon_social"));
                reaseguro.setMonto_seguro(rs.getDouble("monto_seguro"));
                reaseguro.setPorcentaje_cober(rs.getDouble("porcentaje_cober"));
                reaseguro.setNmid(rs.getInt("id_reaseguradora"));
                resultben.setRe(reaseguro);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        } finally {
            rs.close();
            ptsm.close();

        }
        return resultben;
    }

    public void delete(int nmid,Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;

        try{
            String delete = "DELETE FROM public.seguro WHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(delete);
            ptsm.setInt(1,nmid);
            ptsm.executeUpdate();
        }catch (Exception ex){
            throw new DaoExecption(ex);
        } finally {
            ptsm.close();
        }
    }

    public ArrayList<Seguro> getAll(Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        Seguro resultben = null;
        ArrayList<Seguro> list = new ArrayList();
        String SELECT = "SELECT S.nmid,\n" +
                "\tS.tipo_seguro,\n" +
                "\tS.valor,\n" +
                "\tS.descripcion,\n" +
                "\tS.f_inicial,\n" +
                "\tS.f_final,\n" +
                "\tT.nmid AS id_tomador,\n" +
                "    T.documento,\n" +
                "    T.tipo_doc,\n" +
                "    T.nombre,\n" +
                "    T.apellido,\n" +
                "    T.direccion,\n" +
                "    T.telefono,\n" +
                "    T.ocupacion,\n" +
                "    T.correo,\n" +
                "    T.f_naci,\n" +
                "\tR.nmid AS id_reaseguradora,\n" +
                "\tR.nit,\n" +
                "\tR.razon_social,\n" +
                "\tR.monto_seguro,\n" +
                "\tR.porcentaje_cober\n" +
                "\tFROM seguro S\n" +
                "\tINNER JOIN tomador T\n" +
                "\tON S.nmid_tomador = T.nmid\n" +
                "\tINNER JOIN reaseguradora R\n" +
                "\tON S.nmid_reaseguradora = R.nmid";
        try {
            ptsm = con.getConexion().prepareStatement(SELECT);
            rs= ptsm.executeQuery();
            while (rs.next()){
                resultben = new Seguro();
                resultben.setTipo_seguro(rs.getString("tipo_seguro"));
                resultben.setValor(rs.getDouble("valor"));
                resultben.setDescripcion(rs.getString("descripcion"));
                resultben.setF_inicial(rs.getDate("f_inicial"));
                resultben.setF_final(rs.getDate("f_final"));
                Tomador tomador = new Tomador();
                tomador.setDocumento(rs.getString("documento"));
                tomador.setTipo_doc(rs.getString("tipo_doc"));
                tomador.setNombre(rs.getString("nombre"));
                tomador.setApellido(rs.getString("apellido"));
                tomador.setDireccion(rs.getString("direccion"));
                tomador.setTelefono(rs.getString("telefono"));
                tomador.setOcupacion(rs.getString("ocupacion"));
                tomador.setCorreo(rs.getString("correo"));
                tomador.setF_naci(rs.getDate("f_naci"));
                tomador.setNmid(rs.getInt("id_tomador"));
                resultben.setTom(tomador);
                Reaseguro reaseguro = new Reaseguro();
                reaseguro.setNit(rs.getString("nit"));
                reaseguro.setRazon_social(rs.getString("razon_social"));
                reaseguro.setMonto_seguro(rs.getDouble("monto_seguro"));
                reaseguro.setPorcentaje_cober(rs.getDouble("porcentaje_cober"));
                reaseguro.setNmid(rs.getInt("id_reaseguradora"));
                resultben.setRe(reaseguro);
                list.add(resultben);
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
