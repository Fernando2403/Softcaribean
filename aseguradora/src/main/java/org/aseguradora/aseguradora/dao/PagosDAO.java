package org.aseguradora.aseguradora.dao;
import org.aseguradora.aseguradora.exception.DaoExecption;
import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dto.Pagos;
import org.aseguradora.aseguradora.dto.Seguro;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class PagosDAO {

    private static PagosDAO instance;

    public PagosDAO() {
    }

    public static PagosDAO getInstance(){
        if (instance == null){
            instance = new PagosDAO();
        }

        return instance;
    }

    public void Insert(Pagos pagos, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {
            String Insert ="INSERT INTO public.pagos(\n" +
                    "\tf_pago, cuotas, valor_cmes, nmid_seguro)\n" +
                    "\tVALUES (?, ?, ?, ?)";
            ptsm = con.getConexion().prepareStatement(Insert);
            java.sql.Date fecha_pago = new Date(pagos.getF_pago().getTime());
            ptsm.setDate(1, fecha_pago);
            ptsm.setInt(2,pagos.getCuotas());
            ptsm.setDouble(3, pagos.getValor_cmes());
            ptsm.setInt(4,pagos.getSe().getNmid());
            ptsm.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        }finally {
            ptsm.close();
        }
    }

    public void Update(Pagos pagos, Conexion con) throws DaoExecption, SQLException{
        PreparedStatement ptsm = null;
        try{
            String Update="\n" +
                    "UPDATE public.pagos\n" +
                    "\tSET f_pago=?, cuotas=?, valor_cmes=?, nmid_seguro=?\n" +
                    "\tWHERE nmid=?";
            ptsm = con.getConexion().prepareStatement(Update);
            java.sql.Date fecha_pago = new Date(pagos.getF_pago().getTime());
            ptsm.setDate(1, fecha_pago);
            ptsm.setInt(2,pagos.getCuotas());
            ptsm.setDouble(3, pagos.getValor_cmes());
            ptsm.setInt(4,pagos.getSe().getNmid());
            ptsm.setInt(5,pagos.getNmid());
            ptsm.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        }finally {
            ptsm.close();
        }

    }

    public void Delete(int nmid,Conexion con)throws DaoExecption,SQLException{
        PreparedStatement ptsm = null;
        try {
            String Delete="DELETE FROM public.pagos\n" +
                    "\tWHERE nmid=?;";
            ptsm = con.getConexion().prepareStatement(Delete);
            ptsm.setInt(1,nmid);
            ptsm.executeUpdate();
        }catch (Exception ex){
            throw  new DaoExecption(ex);
        }finally {
            ptsm.close();
        }
    }

    public Pagos GetById(int nmid,Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        Pagos resultpagos = null;
        try {
            String SELECT = "SELECT P.nmid,P.f_pago,P.cuotas,P.valor_cmes,A.nmid AS nmid_seguro,\n" +
                    "                     A.tipo_seguro, A.valor AS valor_seguro, A.descripcion AS descripcion_seguro,\n" +
                    "                     A.f_inicial, A.f_final FROM pagos P INNER JOIN seguro A ON P.nmid_seguro = A.nmid WHERE P.nmid=?";
            ptsm = con.getConexion().prepareStatement(SELECT);
            ptsm.setInt(1,nmid);
            rs = ptsm.executeQuery();
            while (rs.next()){
                resultpagos = new Pagos();
                resultpagos.setF_pago(rs.getDate("f_pago"));
                resultpagos.setCuotas(rs.getInt("cuotas"));
                resultpagos.setValor_cmes(rs.getDouble("valor_cmes"));
                Seguro seguro = new Seguro();
                seguro.setNmid(rs.getInt("nmid_seguro"));
                seguro.setTipo_seguro(rs.getString("tipo_seguro"));
                seguro.setValor(rs.getDouble("valor_seguro"));
                seguro.setDescripcion(rs.getString("descripcion_seguro"));
                seguro.setF_inicial(rs.getDate("f_inicial"));
                seguro.setF_final(rs.getDate("f_final"));
                resultpagos.setSe(seguro);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new DaoExecption(ex);
        }finally {
            rs.close();
            ptsm.close();
        }
        return resultpagos;
    }

    public ArrayList<Pagos> GetAll(Conexion con) throws DaoExecption,SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        ArrayList<Pagos> List = new ArrayList();
        try {
            String SELECT="SELECT P.nmid,P.f_pago,P.cuotas,P.valor_cmes,nmid_seguro,A.nmid AS nmid_seguro,\\n\" +\n" +
                    "                     \"A.tipo_seguro, A.valor AS valor_seguro, A.descripcion AS descripcion_seguro,\\n\" +\n" +
                    "                     \"A.f_inicial, A.f_final\" +\n" +
                    "                     \"FROM pagos P INNER JOIN seguro A ON P.nmid_seguro = A.nmid WHERE P.nmid=?";
            ptsm = con.getConexion().prepareStatement(SELECT);
            rs = ptsm.executeQuery();
            while (rs.next()){
                Pagos result = new Pagos();
                result.setF_pago(rs.getDate("f_pago"));
                result.setCuotas(rs.getInt("cuotas"));
                result.setValor_cmes(rs.getDouble("valor_cmes"));
                Seguro seguro = new Seguro();
                seguro.setNmid(rs.getInt("nmid_seguro"));
                seguro.setTipo_seguro(rs.getString("tipo_seguro"));
                seguro.setValor(rs.getDouble("valor_seguro"));
                seguro.setDescripcion(rs.getString("descripcion_seguro"));
                seguro.setF_inicial(rs.getDate("f_inicial"));
                seguro.setF_final(rs.getDate("f_final"));
                result.setSe(seguro);
                List.add(result);
            }
        }catch (Exception ex){
            throw  new DaoExecption(ex);
        }finally {
            rs.close();
            ptsm.close();
        }

        return List;
    }
}
