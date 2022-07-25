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
import model.bangluong;

/**
 *
 * @author PhanKhanh
 */
public class CT_BangLuong {

    public static List<bangluong> findAll() {
        List<bangluong> bangluongList = new ArrayList<>();//taolist doi tuong bl
        Connection cons = null;
        Statement st = null; // sql tinh
        ResultSet rs = null;// ban ghi tra ve ghi thuc hien st
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from bangluong ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //lay du lieu tu rs

                bangluong bl = new bangluong(rs.getInt("mabl"), rs.getString("matx"),
                        rs.getDate("time"), rs.getInt("donht"), rs.getInt("donch"),
                        rs.getInt("donhong"), rs.getInt("donxinnghi"), rs.getDouble("thuong"),
                        rs.getDouble("phat"), rs.getDouble("tongluong"));
                bangluongList.add(bl);
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
        return bangluongList;
    }

    /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them doi tuong bl
    public static void insert(bangluong bl) { //truyen vao doi tuong bl
        Connection cons = null;
        PreparedStatement ps = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into bangluong (matx, time, donht, donch, donhong, "
                    + "donxinnghi, thuong, phat, tongluong) "
                    + "value(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            ps = cons.prepareStatement(sql);
            ps.setString(1, bl.getMatx());
            ps.setDate(2, bl.getTime());
            ps.setInt(3, bl.getDonht());
            ps.setInt(4, bl.getDonch());
            ps.setInt(5, bl.getDonhong());
            ps.setInt(6, bl.getDonxinnghi());
            ps.setDouble(7, bl.getThuong());
            ps.setDouble(8, bl.getPhat());
            ps.setDouble(9, bl.getTongluong());
            ps.executeUpdate(); // thuchien ps
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
    public static void update(bangluong bl) {//truyen vao doi tuong admin
        Connection cons = null;
        PreparedStatement ps = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update bangluong set matx=?, time=?, donht=?, donch=?, "
                    + "donhong=?, donxinnghi=?, thuong=?, phat=?, tongluong=? "
                    + "where mabl = ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, bl.getMatx());
            ps.setDate(2, bl.getTime());
            ps.setInt(3, bl.getDonht());
            ps.setInt(4, bl.getDonch());
            ps.setInt(5, bl.getDonhong());
            ps.setInt(6, bl.getDonxinnghi());
            ps.setDouble(7, bl.getThuong());
            ps.setDouble(8, bl.getPhat());
            ps.setDouble(9, bl.getTongluong());
            ps.setInt(7, bl.getMabl());
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

    public static void delete(bangluong bl) {
        Connection cons = null;
        PreparedStatement ps = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from bangluong where mabl = ?";
            ps = cons.prepareStatement(sql);
            ps.setInt(1, bl.getMabl());
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
    public static List<bangluong> findk(int thang,int nam,String mabl , String matx ) {
        List<bangluong> bangluongList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();//
            String sql = "select*from bangluong where (month(time)=?) and (year(time)=?) and "
                    + "mabl like ? and matx like ?";
            ps = cons.prepareStatement(sql);
            
            ps.setInt(1,thang);
            ps.setInt(2, nam); 
            ps.setString(3,"%" + mabl+"%");
            ps.setString(4,"%" + matx+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                bangluong bl = new bangluong(rs.getInt("mabl"), rs.getString("matx"),
                        rs.getDate("time"), rs.getInt("donht"), rs.getInt("donch"),
                        rs.getInt("donhong"), rs.getInt("donxinnghi"), rs.getDouble("thuong"),
                        rs.getDouble("phat"), rs.getDouble("tongluong"));
                bangluongList.add(bl);
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
        return bangluongList;
    }
    public static List<bangluong> findknottime(String mabl, String matx  ) {
        List<bangluong> bangluongList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();//
            String sql = "select*from bangluong where mabl like ? and matx like ?";
            
            ps = cons.prepareStatement(sql);
            ps.setString(1,"%" + mabl+"%");
            ps.setString(2,"%" + matx+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                bangluong bl = new bangluong(rs.getInt("mabl"), rs.getString("matx"),
                        rs.getDate("time"), rs.getInt("donht"), rs.getInt("donch"),
                        rs.getInt("donhong"), rs.getInt("donxinnghi"), rs.getDouble("thuong"),
                        rs.getDouble("phat"), rs.getDouble("tongluong"));
                bangluongList.add(bl);
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
        return bangluongList;
    }
    public static void main(String[] args) {
        findknottime("1", "sp");
    }
}
