/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Connection.DBconnection;
import static controller.CT_DonHang.tinhdhall;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Shiper;

/**
 *
 * @author PhanKhanh
 */
public class CT_Shiper {

    //lay toan bo d u lieu
    public static List<Shiper> findAll() {
        List<Shiper> shiperList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from shiper ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                Shiper = new Shiper
                Shiper sp = new Shiper(rs.getString("matx"), rs.getDouble("luongcb"),
                        rs.getFloat("sao"), rs.getString("bienxe"), rs.getString("tentx"),
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("Cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                shiperList.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                cons.close();
            } catch (SQLException ex) {
                Logger.getLogger(CT_Shiper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return shiperList;
    }

    /*public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them shipper
    public static void insert(Shiper sp) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into shiper(matx, tentx, gt, tuoi, bienxe, "
                    + "cccd, sdt, diachi, luongcb, sao, user) "
                    + "value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            ps = cons.prepareStatement(sql);
            ps.setString(1, sp.getMatx());
            ps.setString(2, sp.getTen());
            ps.setString(3, sp.getGt());
            ps.setInt(4, sp.getTuoi());
            ps.setString(5, sp.getBienxe());
            ps.setString(6, sp.getCccd());
            ps.setString(7, sp.getSdt());
            ps.setString(8, sp.getDiachi());
            ps.setDouble(9, sp.getLuongcb());
            ps.setFloat(10, sp.getSao());
            ps.setString(11, sp.getUser());
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

    //sua du lieu
    public static void update(Shiper sp) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update shiper set tentx=?, gt=?, tuoi=?, "
                    + "bienxe=?, cccd=?, sdt=?, diachi=?, luongcb=?, sao=?, user=? "
                    + "where matx = ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, sp.getTen());
            ps.setString(2, sp.getGt());
            ps.setInt(3, sp.getTuoi());
            ps.setString(4, sp.getBienxe());
            ps.setString(5, sp.getCccd());
            ps.setString(6, sp.getSdt());
            ps.setString(7, sp.getDiachi());
            ps.setDouble(8, sp.getLuongcb());
            ps.setFloat(9, sp.getSao());
            ps.setString(10, sp.getUser());
            ps.setString(11, sp.getMatx());
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
    public static void updatesao(String matx, float sao) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update shiper set sao=? where matx = ?";
            ps = cons.prepareStatement(sql);
            ps.setFloat(1, sao);
            ps.setString(2,matx );
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

    //xoa du lieu
    public static void delete(String matx) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from shiper where matx = ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1, matx);
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

    public static List<Shiper> finddk(String matx, String tentx, String bienxe, String sdt) {
        List<Shiper> shiperList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            //String sql = "select*from shiper where tentx like ? "; 
            String sql = "select*from shiper where matx like ? and tentx like ? and bienxe like ? and sdt like ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,"%" + matx+ "%");
            ps.setString(2,"%" + tentx+ "%");
            ps.setString(3,"%" + bienxe+ "%");
            ps.setString(4,"%" + sdt+ "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                Shiper sp = new Shiper(rs.getString("matx"), rs.getDouble("luongcb"),
                        rs.getFloat("sao"), rs.getString("bienxe"), rs.getString("tentx"),
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("Cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                shiperList.add(sp);
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
        return shiperList;
    }
    public static double getluongcb(String matx) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from shiper where matx =? ";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                Shiper sp = new Shiper(rs.getString("matx"), rs.getDouble("luongcb"),
                        rs.getFloat("sao"), rs.getString("bienxe"), rs.getString("tentx"),
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("Cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                return sp.getLuongcb();
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
    public static float gsao (String matx) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from shiper where matx =? ";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                Shiper sp = new Shiper(rs.getString("matx"), rs.getDouble("luongcb"),
                        rs.getFloat("sao"), rs.getString("bienxe"), rs.getString("tentx"),
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("Cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                return sp.getSao();
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
    public static float tinhsao(String matx) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        float sao=0;
        int count=0;
        try {
            cons = DBconnection.getConnection();
            String sql = "select sum(sao) from donhang where (matx=? and (mattdh=? or mattdh=? or mattdh=?))";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            ps.setString(2,"ht");
            ps.setString(3,"ch");
            ps.setString(4,"hh");
            rs= ps.executeQuery();
            while (rs.next()) {
               sao = rs.getInt(1);
            }
            //tinh tong sao trong cac don hang
            count=tinhdhall(matx, "ht")+tinhdhall(matx, "ch")+tinhdhall(matx, "hh");
            sao = (sao+CT_Shiper.gsao(matx))/(count+1);//tinh sao = sao hien tai+  tong sao hoa don chia cho tong so don +1
            return sao;
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
    public static Shiper findMatx(String matx) {
        Shiper sp=new Shiper();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            //String sql = "select*from shiper where tentx like ? "; 
            String sql = "select*from shiper where matx =?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                sp = new Shiper(rs.getString("matx"), rs.getDouble("luongcb"),
                        rs.getFloat("sao"), rs.getString("bienxe"), rs.getString("tentx"),
                        rs.getString("gt"), rs.getInt("tuoi"), rs.getString("Cccd"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getString("user"));
                return sp;
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
    return sp;
    }
    public static void main(String[] args) {
        System.out.println(findMatx("sp1").getBienxe());
    }
}
