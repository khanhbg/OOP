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
import model.thuongphat;

/**
 *
 * @author PhanKhanh
 */
public class CT_Thuongphat {
    public static List<thuongphat> findAll() {
        List<thuongphat> thuongphatList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from thuongphat ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                thuongphat tp = new thuongphat(rs.getString("matp"), rs.getString("tentp"),
                        rs.getDouble("sotien"));
                thuongphatList.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                st.close();
                rs.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return thuongphatList;
    }

    /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them tt
    public static void insert(thuongphat tp) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into thuongphat (matp, tentp, sotien)"
                    + "value(?, ?, ?) ";
            ps = cons.prepareStatement(sql);
            ps.setString(1, tp.getMatp());
            ps.setString(2, tp.getTentp());
            ps.setDouble(3, tp.getSotien());
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

    public static void update(thuongphat tp) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql ="update thuongphat set tentp=?, sotien=? where matp=?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,tp.getTentp());
            ps.setDouble(2,tp.getSotien());
            ps.setString(3, tp.getMatp());
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

    public static void delete(String matp) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from ttdh where matp = ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, matp);
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
    public static double gettien(String matp) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from thuongphat where matp=?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, matp);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                thuongphat tp = new thuongphat(rs.getString("matp"), rs.getString("tentp"),
                        rs.getDouble("sotien"));
                return tp.getSotien();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                rs.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(gettien("tcc"));
    }
    
}
