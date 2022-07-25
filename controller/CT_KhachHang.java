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
import model.KhachHang;

/**
 *
 * @author PhanKhanh
 */
public class CT_KhachHang {
    public static List<KhachHang> findAll() {
        List<KhachHang> KhachHangList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from khachhang ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                KhachHang kh = new KhachHang(rs.getString("makh"), rs.getString("tenkh"), 
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                KhachHangList.add(kh);
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
        return KhachHangList;
    }
   /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them kh
    public static void insert(KhachHang kh) {
        Connection cons = null;
        PreparedStatement ps = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into khachhang(makh, tenkh, gt, tuoi, cccd, sdt, "
                    + "diachi, user) "
                    + "value(?, ?, ?, ?, ?, ?, ?, ?) ";
            ps=cons.prepareStatement(sql);
            ps.setString(1, kh.getMakh());
            ps.setString(2,kh.getTen());
            ps.setString(3,kh.getGt());
            ps.setInt(4,kh.getTuoi());
            ps.setString(5,kh.getCccd());
            ps.setString(6,kh.getSdt());
            ps.setString(7,kh.getDiachi());
            ps.setString(8,kh.getUser());
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
    public static void update(KhachHang kh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update khachhang set tenkh=?, gt=?, tuoi=?, cccd=?, "
                    + "sdt=?, diachi=?, user=? "
                    + "where makh = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,kh.getTen());
            ps.setString(2,kh.getGt());
            ps.setInt(3,kh.getTuoi());
            ps.setString(4,kh.getCccd());
            ps.setString(5,kh.getSdt());
            ps.setString(6,kh.getDiachi());
            ps.setString(7,kh.getUser());
            ps.setString(8,kh.getMakh());
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
    public static void delete(String makh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from khachhang where makh = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,makh);
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
    public static List<KhachHang> finddk(String makh, String ten, String sdt, String diachi) {
        List<KhachHang> KhachHangList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from khachhang where makh like ? and tenkh like ? and sdt like? and diachi like ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,"%" + makh+ "%");
            ps.setString(2,"%" + ten+ "%");
            ps.setString(3,"%" + sdt+ "%");
            ps.setString(4,"%" + diachi+ "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                KhachHang kh = new KhachHang(rs.getString("makh"), rs.getString("tenkh"), 
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                KhachHangList.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return KhachHangList;
    }
     public static KhachHang findmakh(String makh) {
        KhachHang kh= new KhachHang();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from khachhang where makh =?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,makh);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                kh = new KhachHang(rs.getString("makh"), rs.getString("tenkh"), 
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                return  kh;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kh;
    }
}
