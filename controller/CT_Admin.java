/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Connection.DBconnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;


public class CT_Admin {
    public static Admin finduser(String user) {
        Admin ad = new Admin();
        Connection cons = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = " select * from admin where user = ? ";
            st = cons.prepareStatement(sql);
            st.setString(1, user);
            rs = st.executeQuery();
            while (rs.next()) {
                //lay du lieu tu rs
                ad = new Admin(rs.getString("maad"), rs.getString("tenad"), 
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                st.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ad;
    }
 
    //them shipper
    public static void insert(Admin ad) { //truyen vao doi tuong admin
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into admin (maad, tenad, gt, tuoi, cccd, sdt, "
                    + "diachi, user) "
                    + "value(?, ?, ?, ?, ?, ?, ?, ?,) ";
            ps=cons.prepareStatement(sql);
            ps.setString(1,ad.getMaad());
            ps.setString(2,ad.getTen());
            ps.setString(3,ad.getGt());
            ps.setInt(4,ad.getTuoi());
            ps.setString(5,ad.getCccd());
            ps.setString(6,ad.getSdt());
            ps.setString(7,ad.getDiachi());
            ps.setString(8,ad.getUser());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                ps.close();//dong PreparedStatement
                cons.close(); //dong ket noi voi csdl
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    //update
    public static void update(Admin ad) {//truyen vao doi tuong admin
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update admin set tenad=?, gt=?, tuoi=?, cccd=?, "
                    + "sdt=?, diachi=?, user=? "
                    + "where maad = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,ad.getTen());
            ps.setString(2,ad.getGt());
            ps.setInt(3,ad.getTuoi());
            ps.setString(4,ad.getCccd());
            ps.setString(5,ad.getSdt());
            ps.setString(6,ad.getDiachi());
            ps.setString(7,ad.getUser());
            ps.setString(8,ad.getMaad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                ps.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void delete(Admin ad) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from admin where maad = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,ad.getMaad());//sp1
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                ps.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void main(String[] args) {
        //System.out.println(finduser("admin1"));
    }
}
