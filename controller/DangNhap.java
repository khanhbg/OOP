/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Connection.DBconnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhanKhanh
 */
public class DangNhap {
    // tra ve lv dang nhap de chuyen luong
    public static int DN(String user, String password ){
        Connection cons=null;
        PreparedStatement ps=null;
        ResultSet rs =null;
        try {
            cons=DBconnection.getConnection();
            String sql = "select*from tkdn where user=? and password=? ";
            ps=cons.prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt("lv");
            }
        } catch (Exception ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null,ex);
        }finally{
            try {
                cons.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0; 
    }
    public static void main(String[] args) {
        String user="admin1";
        String password="123";
        int i= DN(user, password);
        System.out.println(i);
    }
}
