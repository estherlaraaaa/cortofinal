/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexion.Conexion;
import Interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Filtro>{
    private static final String SQL_INSERT = "INSERT INTO alumnos (carnet,nombre,edad,universidad, apellidos) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE alumnos SET apellidos = ?, nombre = ?, edad = ?,universidad=?  WHERE carnet = ?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE carnet = ?";
    private static final String SQL_READ = "SELECT * FROM alumnos";
    private static final String SQL_READALL = "SELECT * FROM alumnos";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Filtro g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCarnet());
            ps.setString(2, g.getUniversidad());
            ps.setString(5, g.getNombre());
            ps.setString(6, g.getApellidos());
            ps.setInt(3, g.getEdad());
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCarnet());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getUniversidad());
            ps.setInt(2, c.getEdad());
            ps.setBoolean(3, c.getExistencia());
            ps.setString(4, c.getNombre());
            ps.setString(5, c.getApellidos());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                f = new Filtro(rs.getString(1), rs.getString(2),rs.getString(3), rs.getInt(4),rs.getString(5) , rs.getBoolean(6));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Filtro(rs.getString(1), rs.getString(2),rs.getString(3), rs.getInt(4),rs.getString(5) , rs.getBoolean(6)));
            }
            rs.close();
        } catch (SQLException ex) {
           Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex); 
        } finally {
            con.cerrarConexion();
        }
        return all;
    }
    
}
