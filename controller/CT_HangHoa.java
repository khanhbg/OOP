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
import model.HangHoa;

/**
 *
 * @author PhanKhanh
 */
public class CT_HangHoa {
    public static List<HangHoa> findAll() {
        List<HangHoa> HangHoaList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from hanghoa ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                HangHoa hh = new HangHoa(rs.getInt("maloaihh"), rs.getString("tenhh"),
                        rs.getDouble("phiship"));
                HangHoaList.add(hh);
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
        return HangHoaList;
    }
    //them hh
    public static void insert(HangHoa hh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into hanghoa(tenhh, phiship)"
                    + "value(?, ?) ";
            ps=cons.prepareStatement(sql);
            ps.setString(1,hh.getTenhh());
            ps.setDouble(2,hh.getPhiship());
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
    public static void update(HangHoa hh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update hanghoa set tenhh=?, phiship=? where maloaihh = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,hh.getTenhh());
            ps.setDouble(2,hh.getPhiship());
            ps.setInt(3,hh.getMaloaihh());
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
    public static void delete(int maloaihh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from hanghoa where maloaihh = ?";
            ps=cons.prepareStatement(sql);
            ps.setInt(1,maloaihh);
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
    public static HangHoa findMaloaihh(int maloaihh) {
        HangHoa hh=new HangHoa();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from hanghoa where maloaihh=? ";
            ps = cons.prepareStatement(sql);
            ps.setInt(1,maloaihh );
            rs = ps.executeQuery();
            while (rs.next()) {
                hh = new HangHoa(rs.getInt("maloaihh"), rs.getString("tenhh"),
                        rs.getDouble("phiship"));
                return hh;
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
        return hh;
    }
}
