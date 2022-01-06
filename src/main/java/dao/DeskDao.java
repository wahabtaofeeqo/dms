/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Desk;

/**
 *
 * @author user
 */
public class DeskDao extends DaoAdapter {
    
    public static List<Desk> getAll() {
        List<Desk> list = new ArrayList<>();
       
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT d.*, b.id as bid FROM desks d LEFT JOIN bookings b ON(d.id = b.desk_id)");
            while(rs.next()) {
                Desk d = new Desk();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setBookable(rs.getBoolean("bookable"));
                d.setBooked(rs.getInt("bid") > 0);
                list.add(d);
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public boolean insert(Object o) {
        try {
            Desk desk = (Desk) o;
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO desks (name, bookable) VALUES(?,?)");
            ps.setString(1, desk.getName());
            ps.setBoolean(2, desk.isBookable());
            
            return ps.executeUpdate() > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object find(int id) {
          
        Desk d = null;
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM desks WHERE id = " + id);
     
            while(rs.next()) {
                d = new Desk();
                d.setId(id);
                d.setName(rs.getString("name"));
                d.setBookable(rs.getBoolean("bookable"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(DeskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            int affected = s.executeUpdate("DELETE FROM desks WHERE id = " + id);
            return affected > 0;
        } 
        catch (SQLException ex) {
            Logger.getLogger(DeskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
