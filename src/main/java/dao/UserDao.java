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
import models.User;

/**
 *
 * @author user
 */
public class UserDao extends BaseDao {
    
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
}
