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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author user
 */
public class UserDao extends DaoAdapter {
    
    public static User getUserWithEMail(String email) {
        
        User user = null;
        Connection con = getConnection();
        
        if(con != null) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ?");
                ps.setString(1, email);
                
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {  
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(email);
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return user;
    }

    @Override
    public List<Object> all() {
        List<Object> list = new ArrayList<>();
       
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM users WHERE role = 'user'");
            while(rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                u.setEmail(rs.getString("email"));
                
                Timestamp t = rs.getTimestamp("createdAt");
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
                u.setCreated(dateFormat.format(t));
                
                list.add(u);
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public boolean update(int id, Object o) {
        try {
            User u = (User) o;
            Connection c = getConnection();
            String sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, gender = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, u.getFirstname());
            ps.setString(2, u.getLastname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getGender());
            ps.setInt(5, id);
            
            return ps.executeUpdate() > 0;
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return false;
    }

    @Override
    public boolean insert(Object o) {
        try {
            User u = (User) o;
            
            String sql = "INSERT INTO users(firstname, lastname, email, password, gender) VALUES(?,?,?,?,?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, u.getFirstname());
            ps.setString(2, u.getLastname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getGender());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
