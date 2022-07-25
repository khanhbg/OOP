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
import model.thongkebaocao;

/**
 *
 * @author PhanKhanh
 */
public class CT_thongkebaocao {
    public static List<thongkebaocao> findAll() {
        List<thongkebaocao> thongkebaocaoList = new ArrayList<>();
        Connection cons = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from thongke ";
            st = cons.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //lay du lieu tu rs
                thongkebaocao bc = new thongkebaocao(rs.getInt("mabc"), rs.getDate("time"),
                        rs.getInt("tdht"), rs.getInt("tdch"), rs.getInt("tdhh"),rs.getInt("tddg"),
                        rs.getDouble("tdoanhthu"), rs.getDouble("quyluongsp"), 
                        rs.getDouble("doanhthu"));
                thongkebaocaoList.add(bc);
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
        return thongkebaocaoList;
    }
    public static List<thongkebaocao> findtime(int nam) {
        List<thongkebaocao> thongkebaocaoList = new ArrayList<>();
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "select*from thongke where (year(time))=?";
            ps = cons.prepareStatement(sql);
            ps.setInt(1,nam);
            rs = ps.executeQuery();
            while (rs.next()) {
                //lay du lieu tu rs
                thongkebaocao bc = new thongkebaocao(rs.getInt("mabc"), rs.getDate("time"),
                        rs.getInt("tdht"), rs.getInt("tdch"), rs.getInt("tdhh"),rs.getInt("tddg"),
                        rs.getDouble("tdoanhthu"), rs.getDouble("quyluongsp"), 
                       rs.getDouble("doanhthu"));
                thongkebaocaoList.add(bc);
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
        return thongkebaocaoList;
    }
 
    public static double tongdoanhthu(int month, int year) {
        double tongdoanhthu=0;
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sodonht;
        try {
            cons = DBconnection.getConnection();
            String sql = "select sum(phiship) from donhang inner join hanghoa on hanghoa.maloaihh = donhang.maloaihh "
                    + "where (month(tggiao))=? and (year(tggiao))=?  ";
            ps = cons.prepareStatement(sql);
            ps.setInt(1,month );
            ps.setInt(2,year);
            rs= ps.executeQuery();
            while (rs.next()) {
                tongdoanhthu=rs.getDouble(1);      
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
        return tongdoanhthu;
    }
    public static double quyluongsp(int thang,int nam) {
        double quyluongsp=0;
        Connection cons = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();//
            String sql = "select sum(tongluong) from bangluong where (month(time)=?) and (year(time)=?)";
            ps = cons.prepareStatement(sql);         
            ps.setInt(1,thang);
            ps.setInt(2, nam); 
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("DiaChi"));
                quyluongsp=rs.getDouble(1);
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
        return quyluongsp;
    }
    public static double doanhthu(int thang,int nam){
        double doanhthu= tongdoanhthu(thang, nam)-quyluongsp(thang, nam);
        return doanhthu;
    }
    public static void insert(thongkebaocao tkbc) {
        Connection cons = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            cons = DBconnection.getConnection();
            String sql = "insert into thongke (time, tdht, tdch,tdhh,tdoanhthu,quyluongsp,tddg,doanhthu)"
                    + "value(?, ?, ? , ? , ? , ? , ? , ? ) ";
            ps = cons.prepareStatement(sql);
            ps.setDate(1, tkbc.getTime());
            ps.setInt(2, tkbc.getTdht());
            ps.setInt(3, tkbc.getTdch());
            ps.setInt(4, tkbc.getTdhh());
            ps.setDouble(5, tkbc.getTongdoanhthu());
            ps.setDouble(6, tkbc.getQuyluongsp());
            ps.setInt(7, tkbc.getTddg());
            ps.setDouble(8,tkbc.getDoanhthu());
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
         //List<thongkebaocao> thongkebaocaoList = new ArrayList<>();
         //thongkebaocaoList = CT_thongkebaocao.findAll();
         //System.out.println(quyluongsp(8, 2021));
         //System.out.println(doanhthu(8, 2021));
    }
}
