/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public abstract class BaseDao {
    protected static Connection getConnection() {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dms", "root", "");
        }
        catch (SQLException ex) {
            System.err.println("No Connected to Database");
        }
        
        return con;
    }
    
    public abstract boolean insert(Object o);
    public abstract Object find(int id);
    public abstract boolean delete(int id);
    public abstract List<Object> all();
    public abstract boolean update(int id, Object o);
}
