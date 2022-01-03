/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
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
public class DeskDao extends BaseDao {
    
    public static List<Desk> getAll() {
        List<Desk> list = new ArrayList<>();
       
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM desks");
            while(rs.next()) {
                Desk d = new Desk();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setBookable(rs.getBoolean("bookable"));
                
                list.add(d);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(DeskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
