/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Connection.DBconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DonHang;
import model.Shiper;

/**
 *
 * @author PhanKhanh
 */
public class CT_DonHang {
    public static List<DonHang> findAll() {
        List<DonHang> donhangList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from donhang ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                DonHang dh = new DonHang(rs.getInt("madh"), rs.getString("matx"), rs.getInt("maloaihh"), 
                        rs.getString("makh"), rs.getString("diachigiao"), 
                        rs.getString("mattdh"), rs.getDate("tggiao"), 
                        rs.getDouble("giatrihh"), rs.getFloat("sao"));
                donhangList.add(dh);
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
        return donhangList;
    }
   /* public static void main(String[] args) {
        System.out.println(findAll());
    }*/
    //them dh
    public static void insert(DonHang dh) {
        Connection cons = null;
        PreparedStatement ps = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into donhang (matx, maloaihh, makh, diachigiao"
                    + ", mattdh, tggiao, giatrihh, sao) "
                    + "value(?, ?, ?, ?, ?, ?, ?, ?) ";
            ps=cons.prepareStatement(sql);
            ps.setString(1,dh.getMatx());
            ps.setInt(2,dh.getMaloaihh());
            ps.setString(3,dh.getMakh());
            ps.setString(4,dh.getDiachigiao());
            ps.setString(5,dh.getMattdh());
            //SimpleDateFormat dt =new SimpleDateFormat("yyyy-MM-dd");
            //String date =dt.format(dh.getTggiao());
            ps.setDate(6,dh.getTggiao());
            ps.setDouble(7,dh.getGiatrihh());
            ps.setFloat(8,dh.getSao());
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
    public static void update(DonHang dh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "update donhang set matx=?, maloaihh=?, makh=?, diachigiao=?"
                    + ", mattdh=?, tggiao=?, giatrihh=?, sao=?"
                    + "where madh = ?";
            ps=cons.prepareStatement(sql);
            ps.setString(1,dh.getMatx());
            ps.setInt(2,dh.getMaloaihh());
            ps.setString(3,dh.getMakh());
            ps.setString(4,dh.getDiachigiao());
            ps.setString(5,dh.getMattdh());
            ps.setDate(6,dh.getTggiao());
            ps.setDouble(7,dh.getGiatrihh());
            ps.setFloat(8,dh.getSao());
            ps.setInt(9,dh.getMadh());
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
    public static void delete(int madh) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "delete from donhang where madh = ?";
            ps=cons.prepareStatement(sql);
            ps.setInt(1,madh);
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
    public static List<DonHang> findName(String matx) {
        List<DonHang> donhangList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from donhang where matx=?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                DonHang dh = new DonHang(rs.getInt("madh"), rs.getString("matx"), rs.getInt("maloaihh"), 
                        rs.getString("makh"), rs.getString("diachigiao"), 
                        rs.getString("mattdh"), rs.getDate("tggiao"), 
                        rs.getDouble("giatrihh"), rs.getFloat("sao"));
                donhangList.add(dh);
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
        return donhangList;
    }
    public static List<DonHang> findk(java.sql.Date tggiaotu, java.sql.Date tggiaoden,String madh, String matx, String mattdh, String diachi ) {
        List<DonHang> donhangList = new ArrayList<>();//java.sql.Date tggiao,
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();//
            String sql = "select*from donhang where (tggiao between ? and ? ) and madh like ? "
                    + "and matx like ? and mattdh like ? and diachigiao like ?";
            ps = cons.prepareStatement(sql);
            ps.setDate(1,tggiaotu);
            ps.setDate(2, tggiaoden);
            ps.setString(3,"%" + madh+"%");
            ps.setString(4,"%" + matx+"%");
            ps.setString(5,"%" + mattdh+"%");
            ps.setString(6,"%" + diachi+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                DonHang dh = new DonHang(rs.getInt("madh"), rs.getString("matx"), rs.getInt("maloaihh"), 
                        rs.getString("makh"), rs.getString("diachigiao"), 
                        rs.getString("mattdh"), rs.getDate("tggiao"), 
                        rs.getDouble("giatrihh"), rs.getFloat("sao"));
                donhangList.add(dh);
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
        return donhangList;
    }
     public static List<DonHang> findknotime(String madh, String matx, String mattdh, String diachi ) {
        List<DonHang> donhangList = new ArrayList<>();//java.sql.Date tggiao,
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();//
            String sql = "select*from donhang where madh like ? "
                    + "and matx like ? and mattdh like ? and diachigiao like ?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,"%" + madh+"%");
            ps.setString(2,"%" + matx+"%");
            ps.setString(3,"%" + mattdh+"%");
            ps.setString(4,"%" + diachi+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                DonHang dh = new DonHang(rs.getInt("madh"), rs.getString("matx"), rs.getInt("maloaihh"), 
                        rs.getString("makh"), rs.getString("diachigiao"), 
                        rs.getString("mattdh"), rs.getDate("tggiao"), 
                        rs.getDouble("giatrihh"), rs.getFloat("sao"));
                donhangList.add(dh);
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
        return donhangList;
    }
    public static List<DonHang> findmattdh(String matx, String mattdh) {
        List<DonHang> donhangList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();//
            String sql = "select*from donhang where  matx=? and mattdh=?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            ps.setString(2,mattdh);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                DonHang dh = new DonHang(rs.getInt("madh"), rs.getString("matx"), rs.getInt("maloaihh"), 
                        rs.getString("makh"), rs.getString("diachigiao"), 
                        rs.getString("mattdh"), rs.getDate("tggiao"), 
                        rs.getDouble("giatrihh"), rs.getFloat("sao"));
                donhangList.add(dh);
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
        return donhangList;
    }
    //tinh so luong cac don hang
    public static int tinhdonhang(String matx, String mattdh, int month, int year) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sodonht=0;
        try {
            cons = DBconnection.getConnection();
            String sql = "select count(madh) from donhang where matx=? and mattdh=? and (month(tggiao))=? and (year(tggiao))=?  ";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            ps.setString(2,mattdh );
            ps.setInt(3,month );
            ps.setInt(4,year);
            rs= ps.executeQuery();
            while (rs.next()) {
               sodonht=rs.getInt(1);
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
        return sodonht;
    }
    //tinh don hang alltime
    public static int tinhdhall(String matx, String mattdh) {
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sodonht;
        try {
            cons = DBconnection.getConnection();
            String sql = "select count(madh) from donhang where matx=? and mattdh=?";
            ps = cons.prepareStatement(sql);
            ps.setString(1,matx);
            ps.setString(2,mattdh );
            rs= ps.executeQuery();
            while (rs.next()) {
               sodonht=rs.getInt(1);
               return  sodonht;
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
        //System.out.println(tinhsao("sp1"));
        //System.out.println(tinhdhall("sp1", "dg"));
         long millis=System.currentTimeMillis();
         long ml=System.currentTimeMillis();
         Date tu =new Date(millis);
         Date den=new Date(ml);
        //DonHang dh1=new DonHang("sp1", 1, "kh1", "diachigiao", "ht", date, 50000, 5);
       List<DonHang> donhangList = new ArrayList<>();
        donhangList = findk(tu,den, "", "", "", "");
        for(DonHang dh : donhangList){
        System.out.println(dh.getTggiao());
        }
        
        //insert(dh);
    }
     public static DonHang findmadh(int madh) {
        DonHang dh = new DonHang();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from donhang where madh=?";
            ps = cons.prepareStatement(sql);
            ps.setInt(1,madh);
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                dh = new DonHang(rs.getInt("madh"), rs.getString("matx"), rs.getInt("maloaihh"), 
                        rs.getString("makh"), rs.getString("diachigiao"), 
                        rs.getString("mattdh"), rs.getDate("tggiao"), 
                        rs.getDouble("giatrihh"), rs.getFloat("sao"));
                return  dh;
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
        return dh;
    }
}
