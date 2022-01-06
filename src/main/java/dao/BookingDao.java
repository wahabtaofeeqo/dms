/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Booking;
import models.Desk;
import models.User;

/**
 *
 * @author user
 */
public class BookingDao extends DaoAdapter {
    
    public static boolean addBooking(int userId, int deskId, String date, String time) {
        
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO bookings (user_id, desk_id, date, time) VALUES(?,?,?,?)");
            ps.setInt(1, userId);
            ps.setInt(2, deskId);
            ps.setString(3, date);
            ps.setString(4, time);
            
            return ps.executeUpdate() > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static List<Booking> getBookings(int id) {
        try {
            List<Booking> list = new ArrayList<>();
            
            Connection c = getConnection();
            
            String sql = "SELECT b.*, u.id as uid, u.firstname, d.id as did, d.name FROM bookings b, users u, desks d WHERE b.user_id = u.id AND b.desk_id = d.id";
            if(id != 0) {
                sql = sql + " AND b.user_id = " + id;
            }
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            
            while (rs.next()) {
                
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setDate(rs.getString("date"));
                b.setTime(rs.getString("time"));
                
                User u = new User();
                u.setId(rs.getInt("uid"));
                u.setFirstname(rs.getString("firstname"));
                
                Desk d = new Desk();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("name"));
                
                b.setUser(u);
                b.setDesk(d);
                
                list.add(b);
            }
            return list;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
