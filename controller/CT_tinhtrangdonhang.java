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
import model.tinhtrangdonhang;

/**
 *
 * @author PhanKhanh
 */
public class CT_tinhtrangdonhang {

    public static List<tinhtrangdonhang> findAll() {
        List<tinhtrangdonhang> tinhtrangList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from ttdh ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                tinhtrangdonhang tt = new tinhtrangdonhang(rs.getString("mattdh"), 
                        rs.getString("tenttdh"));
                tinhtrangList.add(tt);
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
        return tinhtrangList;
    }

    /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them tt
    public static void insert(tinhtrangdonhang tt) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into ttdh (mattdh, tenttdh)"
                    + "value(?, ?) ";
            ps = cons.prepareStatement(sql);
            ps.setString(1, tt.getMattdh());
            ps.setString(2, tt.getTenttdh());
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

    public static void update(tinhtrangdonhang tt) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql ="update ttdh set tenttdh=? where mattdh =?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,tt.getTenttdh());
            ps.setString(3,tt.getMattdh());
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

    public static void delete(String mattdh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from ttdh where mattdh = ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, mattdh);
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
     public static tinhtrangdonhang findmattdh(String mattdh) {
        tinhtrangdonhang ttdh = new tinhtrangdonhang();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from ttdh where mattdh=?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, mattdh);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                ttdh = new tinhtrangdonhang(rs.getString("mattdh"), 
                        rs.getString("tenttdh"));
                return ttdh;
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
        return ttdh;
    }public static void main(String[] args) {
            System.out.println(findmattdh(""));
    }
     
}
