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
import model.Shiper;
import model.bangluong;
import controller.CT_DonHang;
import model.DonHang;
import model.thuongphat;

/**
 *
 * @author PhanKhanh
 */
public class CT_TinhLuong {

    //tinh thuong
    public static double tinhthuong(String matx, int month, int year) {
        double thuong = 0;
        double tcc = 0;
        double tsl = 0;
        int donht = 0;
        int donch = 0;
        int donxinnghi = CT_Donxinnghi.tinhdonnghi(matx, month, year);
        try {
            donht = CT_DonHang.tinhdonhang(matx, "ht", month, year);//tinh don ht
            donch = CT_DonHang.tinhdonhang(matx, "ch", month, year);
            //System.out.println(donch);
            if (donxinnghi <= 2) {// kiem tra don xin nghi
                tcc = CT_Thuongphat.gettien("tcc");//tinh tien chuyen can
            }

            //kiemtra co duoc thuong >1000don
            if ((donht + donch) >= 1000) {
                tsl = CT_Thuongphat.gettien("tsl");//tinh tien doanh so
            }
            //thuong=tiendonhoanthanh + tiendonchuyenhoan + tienchuyencan+tiensoluong
            thuong = donht * CT_Thuongphat.gettien("tdht") + donch * CT_Thuongphat.gettien("tdch") + tcc + tsl;
            return thuong;
        } catch (Exception ex) {
            System.out.println("err");
        }
        return 0;
    }

    //tinhphat
    public static double tinhphat(String matx, int month, int year) {
        double phat = 0;
        double pnghi = 0;
        double phh = 0;
        int donxinnghi = CT_Donxinnghi.tinhdonnghi(matx, month, year);
        List<DonHang> donhangList = new ArrayList<>();
        donhangList=CT_DonHang.findmattdh(matx,"hh");
        try {
            //tinh tong tien cac don hong
            for (DonHang dh : donhangList) {
                phh = phh + dh.getGiatrihh();
            }
            //shiper chiu phat = tong tien * muc den(0.5)
            phh=phh*CT_Thuongphat.gettien("phh");
            //System.out.println(donch);
            if (donxinnghi > 2) {// kiem tra don xin nghi
                pnghi = CT_Thuongphat.gettien("pnghi") * (donxinnghi - 2);//tinh tien chuyen can
            }
            phat=phh+pnghi;
            return phat;
        } catch (Exception ex) {
            System.out.println("err");
        }
        return 0;
    }
    public static double tinhluong(String matx, int month, int year){
        double tienluong=0;
        try {
            //luong= luongcb+thuong+phat
            tienluong=CT_Shiper.getluongcb(matx)+tinhthuong(matx, month, year)+tinhphat(matx, month, year);
            return tienluong;
        } catch (Exception e) {
            System.out.println("err");
        }
        return 0;
    }

    public static void main(String[] args) {
        //System.out.println(tinhthuong("sp1", "8", "2021"));
        //System.out.println(tinhphat("sp1", "8", "2021"));
        System.out.println(tinhluong("sp1", 8, 2021));
    }

}
