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
import model.Donxinnghi;

/**
 *
 * @author PhanKhanh
 */
public class CT_Donxinnghi {
    public static List<Donxinnghi> findAll() {
        List<Donxinnghi> donxinnghiList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from donxinnghi ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                Donxinnghi dxn = new Donxinnghi(rs.getInt("madxn"), rs.getString("matx"),
                        rs.getDate("time"));
                donxinnghiList.add(dxn);
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
        return donxinnghiList;
    }

    /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them tt
    public static void insert(Donxinnghi dxn) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into thuongphat (matx, time)"
                    + "value(?, ?) ";
            ps = cons.prepareStatement(sql);
            ps.setString(1, dxn.getMatx());
            ps.setDate(2, dxn.getTime());
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

    public static void update(Donxinnghi dxn) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql ="update thuongphat set matx=?, time=? where madxn=?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,dxn.getMatx());
            ps.setDate(2,dxn.getTime());
            ps.setInt(3, dxn.getMadxn());
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

    public static void delete(int madxn) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from ttdh where madxn = ?";
            ps = cons.prepareStatement(sql);
            ps.setInt(1, madxn);
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
    public static int tinhdonnghi(String matx, int month, int year) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sodonnghi;
        try {
            cons = DBconnection.getConnection();
            String sql = "select count(madxn) from donxinnghi where matx=? and month(time)=? and year(time)=? ";
            ps = cons.prepareStatement(sql);
            ps.setString(1, matx);
            ps.setInt(2,month );
            ps.setInt(3, year);
            rs= ps.executeQuery();
            while (rs.next()) {
               sodonnghi=rs.getInt(1);
               return  sodonnghi;
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
        return 0;
    }
    public static void main(String[] args) {
    }    
}
