/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TKDangNhap;

/**
 *
 * @author PhanKhanh
 */
public class CT_TKDangNhap {
   public static List<TKDangNhap> findAll() {
        List<TKDangNhap> TKList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from tkdn ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                TKDangNhap tk= new TKDangNhap(rs.getString("user"), 
                        rs.getString("password"), rs.getInt("lv"));
                TKList.add(tk);
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
        return TKList;
    }
   public static TKDangNhap finduser(String user) {
        TKDangNhap tk=new TKDangNhap();
        Connection cons = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from tkdn where user=? ";
            st = cons.prepareStatement(sql);
            st.setString(1, user);
            rs = st.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                tk= new TKDangNhap(rs.getString("user"), 
                        rs.getString("password"), rs.getInt("lv"));
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
        return tk;
    }
   /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them TK
    public static void insert(TKDangNhap tk) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into tkdn (user, password, lv)"
                    + "value(?, ?, ?) ";
            ps=cons.prepareStatement(sql);
            ps.setString(1,tk.getUser());
            ps.setString(2,tk.getPassword());
            ps.setInt(3,tk.getLv());
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
    public static void update(TKDangNhap tk) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update tkdn set password=?, lv=? where user =?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,tk.getPassword());
            ps.setInt(2,tk.getLv());
            ps.setString(3,tk.getUser());
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
    public static void delete(String user) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from tkdn where user = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,user);
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
    
}
