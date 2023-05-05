package org.aseguradora.aseguradora.dao;

import org.aseguradora.aseguradora.bd.Conexion;
import org.aseguradora.aseguradora.dto.Reaseguro;
import org.aseguradora.aseguradora.exception.DaoExecption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ReaseguroDAO {
    private static ReaseguroDAO instance;

    private ReaseguroDAO() {


    }

    public static ReaseguroDAO getInstance() {
        if (instance == null) {
            instance = new ReaseguroDAO();
        }
        return instance;
    }

    public void Insert(Reaseguro reaseguradora, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {
            String Insert = "INSERT INTO public.reaseguradora(\n" +
                    "\t nit, razon_social, monto_seguro, porcentaje_cober)\n" +
                    "\tVALUES (?, ?, ?, ?);";

            ptsm = con.getConexion().prepareStatement(Insert);
            ptsm.setString(1, reaseguradora.getNit());
            ptsm.setString(2, reaseguradora.getRazon_social());
            ptsm.setDouble(3, reaseguradora.getMonto_seguro());
            ptsm.setDouble(4, reaseguradora.getPorcentaje_cober());
            ptsm.executeUpdate();

        } catch (Exception e) {
            throw new DaoExecption(e);
        } finally {
            ptsm.close();
        }

    }

    public void Update(Reaseguro reaseguradora, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;

        try {
            String Update = "UPDATE public.reaseguradora\n" +
                    "\tSET  nit=?, razon_social=?, monto_seguro=?, porcentaje_cober=?\n" +
                    "\tWHERE nmid=?;";
            ptsm = con.getConexion().prepareStatement(Update);
            ptsm.setString(1, reaseguradora.getNit());
            ptsm.setString(2, reaseguradora.getRazon_social());
            ptsm.setDouble(3, reaseguradora.getMonto_seguro());
            ptsm.setDouble(4, reaseguradora.getPorcentaje_cober());
            ptsm.setInt(5, reaseguradora.getNmid());
            ptsm.executeUpdate();

        } catch (SQLException e) {
            throw new DaoExecption(e);
        } finally {
            if (ptsm != null) {
                ptsm.close();
            }
        }

    }

    public void Delete(int nmid, Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        try {
            String delete = "DELETE FROM public.reaseguradora\n" +
                    "\tWHERE nmid=?;";
            ptsm = con.getConexion().prepareStatement(delete);
            ptsm.setInt(1, nmid);
            ptsm.executeUpdate();

        } catch (Exception e) {
            throw new DaoExecption(e);
        } finally {
            ptsm.close();
        }

    }

    public Reaseguro ObgetByIde(int nmid, Conexion con) throws DaoExecption, SQLException {
        String SQL = "\n" +
                "SELECT R.nit,\n" +
                "\tR.razon_social,\n" +
                "\tR.monto_seguro,\n" +
                "\tR.porcentaje_cober\n" +
                "\tFROM reaseguradora R\n" +
                "\tWHERE R.nmid = ?\n";
        Reaseguro resulRea = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {

            stm = con.getConexion().prepareStatement(SQL);
            stm.setInt(1, nmid);
            rs = stm.executeQuery();
            while (rs.next()) {
                resulRea = new Reaseguro();
                resulRea.setNmid(rs.getInt(nmid));
                resulRea.setNit(rs.getString("nit"));
                resulRea.setRazon_social(rs.getString("razon_social"));
                resulRea.setMonto_seguro(rs.getDouble("monto_seguro"));
                resulRea.setPorcentaje_cober(rs.getDouble("porcentaje_cober"));
            }

        } catch (Exception e) {
            throw new DaoExecption(e);
        } finally {
            rs.close();
            stm.close();
        }
        return resulRea;


    }

    public ArrayList<Reaseguro> getAll(Conexion con) throws DaoExecption, SQLException {
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        ArrayList<Reaseguro> list = new ArrayList();
        String SELECT = "SELECT R.nmid,\n" +
                "\tR.nit,\n" +
                "\tR.razon_social,\n" +
                "\tR.monto_seguro,\n" +
                "\tR.porcentaje_cober\n" +
                "\tFROM reaseguradora R";
        try {
            ptsm = con.getConexion().prepareStatement(SELECT);
            rs = ptsm.executeQuery();
            while (rs.next()) {
                Reaseguro rea = new Reaseguro();
                rea.setNmid(rs.getInt("nmid"));
                rea.setNit(rs.getString("nit"));
                rea.setRazon_social(rs.getString("razon_social"));
                rea.setMonto_seguro(rs.getDouble("monto_seguro"));
                rea.setPorcentaje_cober(rs.getDouble("porcentaje_cober"));
                list.add(rea);

            }

        } catch (Exception e) {
            throw new DaoExecption(e);
        } finally {
            rs.close();
            ptsm.close();

        }
        return list;
    }
}
