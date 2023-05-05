package org.aseguradora.aseguradora.dao;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dto.Beneficiario;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.DaoExecption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BeneficiarioDAO {
    private static BeneficiarioDAO instance;
    private TomadorDAO tomadorDAO;
    private BeneficiarioDAO(){

    }
    public static BeneficiarioDAO getInstance(){
        if(instance == null){
            instance = new BeneficiarioDAO();
        }
        return instance;
    }
    public void insert(Beneficiario beneficiario, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm= null;
        String Insert = "INSERT INTO beneficiario(\n" +
                " tipo_doc,documento, nombre, apellido, f_naci, parentezco,ocupacion,direccion, telefono, correo, porcentaje_afi,nombre_banco, numero_cuenta,nmid_tomador  )\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?);";
        try {
            ptsm = con.getConexion().prepareStatement(Insert);
            ptsm.setString(1,beneficiario.getTipo_doc());
            ptsm.setString(2,beneficiario.getDocumento());
            ptsm.setString(3,beneficiario.getNombre());
            ptsm.setString(4,beneficiario.getApellido());
            java.sql.Date utilDate = new java.sql.Date(beneficiario.getF_naci().getTime());
            ptsm.setDate(5,utilDate);
            ptsm.setString(6,beneficiario.getParentezco());
            ptsm.setString(7,beneficiario.getOcupacion());
            ptsm.setString(8,beneficiario.getDireccion());
            ptsm.setString(9,beneficiario.getTelefono());
            ptsm.setString(10,beneficiario.getCorreo());
            ptsm.setDouble(11,beneficiario.getPorcentaje_afi());
            ptsm.setString(12,beneficiario.getNombre_banco());
            ptsm.setInt(13,beneficiario.getNumero_cuenta());
            ptsm.setInt(14,beneficiario.getNmid_tomador().getNmid());
            ptsm.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        }finally {
            ptsm.close();
        }


    }
    public void update(Beneficiario beneficiario, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {

            String Update = "UPDATE public.beneficiario SET  tipo_doc=?,documento=?, nombre=?, apellido=?, f_naci=?, parentezco=?,ocupacion=?,direccion=?, telefono=?, correo=?, porcentaje_afi=?,nombre_banco=?, numero_cuenta=?,nmid_tomador=? WHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(Update);
            ptsm.setString(1,beneficiario.getTipo_doc());
            ptsm.setString(2,beneficiario.getDocumento());
            ptsm.setString(3,beneficiario.getNombre());
            ptsm.setString(4,beneficiario.getApellido());
            java.sql.Date utilDate = new java.sql.Date(beneficiario.getF_naci().getTime());
            ptsm.setDate(5,utilDate);
            ptsm.setString(6,beneficiario.getParentezco());
            ptsm.setString(7,beneficiario.getOcupacion());
            ptsm.setString(8,beneficiario.getDireccion());
            ptsm.setString(9,beneficiario.getTelefono());
            ptsm.setString(10,beneficiario.getCorreo());
            ptsm.setDouble(11,beneficiario.getPorcentaje_afi());
            ptsm.setString(12,beneficiario.getNombre_banco());
            ptsm.setInt(13,beneficiario.getNumero_cuenta());


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoExecption(ex);
        } finally {
            if (ptsm != null) {
                ptsm.close();
            }
        }
    }
    public Beneficiario getById(int nmid,Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        Beneficiario resultben = null;
        String SELECTBYID = "SELECT A.nmid, \n" +
        " A.tipo_doc,\n" +
                "\t A.documento,\n" +
                "\t A.nombre,\n" +
                "\t A.apellido,\n" +
                "\t A.f_naci,\n" +
                "\t A.parentezco,\n" +
                "\t A.ocupacion,\n" +
                "\t A.direccion,\n" +
                "\t A.telefono,\n" +
                "\t A.correo,\n" +
                "\t A.porcentaje_afi,\n" +
                "\t A.nombre_banco,\n" +
                "\t A.numero_cuenta,\n" +
                "\t A.nmid_tomador,\n" +
                "\t B.nmid AS idtomador,\n" +
                "\t B.documento AS documento_tom,\n" +
                "\t B.tipo_doc AS tipo_doc_tom,\n" +
                "\t B.nombre AS nombre_tom,\n" +
                "\t B.apellido AS apellido_tom,\n" +
                "\t B.direccion AS direccion_tom,\n" +
                "\t B.telefono AS telefono_tom,\n" +
                "\t B.ocupacion AS ocupacion_tom,\n" +
                "\t B.correo AS correo_tom,\n" +
                "\t B.f_naci AS f_naci_tom\n" +
               " FROM beneficiario A \n" +
                "INNER JOIN \n" +
                " tomador B ON A.nmid_tomador=B.nmid \n" +
                "WHERE A.nmid=? \t" ;
        try {
            ptsm = con.getConexion().prepareStatement(SELECTBYID);
            ptsm.setInt(1,nmid);
            rs = ptsm.executeQuery();
            while (rs.next()){
                resultben = new Beneficiario();
                resultben.setTipo_doc(rs.getString("tipo_doc"));
                resultben.setDocumento(rs.getString("documento"));
                resultben.setNombre(rs.getString("nombre"));
                resultben.setApellido(rs.getString("apellido"));
                resultben.setF_naci(rs.getDate("f_naci"));
                resultben.setParentezco(rs.getString("parentezco"));
                resultben.setOcupacion(rs.getString("ocupacion"));
                resultben.setDireccion(rs.getString("direccion"));
                resultben.setTelefono(rs.getString("telefono"));
                resultben.setCorreo(rs.getString("correo"));
                resultben.setNombre_banco(rs.getString("nombre_banco"));
                resultben.setNumero_cuenta(rs.getInt("numero_cuenta"));
                Tomador tomador = new Tomador();
                tomador.setDocumento(rs.getString("documento_tom"));
                tomador.setTipo_doc(rs.getString("tipo_doc_tom"));
                tomador.setNombre(rs.getString("nombre_tom"));
                tomador.setApellido(rs.getString("apellido_tom"));
                tomador.setDireccion(rs.getString("direccion_tom"));
                tomador.setTelefono(rs.getString("telefono_tom"));
                tomador.setCorreo(rs.getString("ocupacion_tom"));
                tomador.setCorreo(rs.getString("correo_tom"));
                tomador.setF_naci(rs.getDate("f_naci_tom"));
                tomador.setNmid(rs.getInt("idtomador"));
                resultben.setNmid_tomador(tomador);

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
            String delete = "DELETE FROM public.beneficiario WHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(delete);
            ptsm.setInt(1,nmid);
            ptsm.executeUpdate();
        }catch (Exception ex){
            throw new DaoExecption(ex);
        } finally {
            ptsm.close();
        }
    }

    public ArrayList<Beneficiario> getAll(Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        ArrayList<Beneficiario> list = new ArrayList();
        String SELECT = "SELECT tipo_doc,documento, nombre, apellido, f_naci, parentezco,ocupacion,direccion, telefono, correo, porcentaje_afi,nombre_banco, numero_cuenta,nmid_tomador FROM public.beneficiario";
        try {
            ptsm = con.getConexion().prepareStatement(SELECT);
            rs= ptsm.executeQuery();
            while (rs.next()){
               Beneficiario beneficiario = new Beneficiario();
               Tomador tom =new Tomador();
                beneficiario.setTipo_doc(rs.getString("tipo_doc"));
                beneficiario.setDocumento(rs.getString("documento"));
                beneficiario.setNombre(rs.getString("nombre"));
                beneficiario.setApellido(rs.getString("apellido"));
                beneficiario.setF_naci(rs.getDate("f_naci"));
                beneficiario.setParentezco(rs.getString("parentezco"));
                beneficiario.setOcupacion(rs.getString("ocupacion"));
                beneficiario.setCorreo(rs.getString("correo"));
                beneficiario.setPorcentaje_afi(rs.getDouble("porcentaje_afi"));
                beneficiario.setNombre_banco(rs.getString("nombre_banco"));
                beneficiario.setNumero_cuenta(rs.getInt("numero_cuenta"));
                beneficiario.setNmid_tomador(tom);
                list.add(beneficiario);
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
