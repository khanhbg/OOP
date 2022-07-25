/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
/**
 *
 * @author PhanKhanh
 */
public class bangluong {
    private int mabl;
    private String matx;
    private Date time ;
    private int donht; //donhoanthanh
    private int donch; // donchuyenhoan
    private int donhong; //donhuhong
    private int donxinnghi; //donxinnghi
    private double thuong;
    private double phat;
    private double tongluong;
    
    public bangluong(){
        
    }

    public bangluong(String matx, Date time, int donht, int donch, int donhong, int donxinnghi, double thuong, double phat, double tongluong) {
        this.matx = matx;
        this.time = time;
        this.donht = donht;
        this.donch = donch;
        this.donhong = donhong;
        this.donxinnghi = donxinnghi;
        this.thuong = thuong;
        this.phat = phat;
        this.tongluong = tongluong;
    }

    public bangluong(int mabl, String matx, Date time, int donht, int donch, int donhong, int donxinnghi, double thuong, double phat, double tongluong) {
        this.mabl = mabl;
        this.matx = matx;
        this.time = time;
        this.donht = donht;
        this.donch = donch;
        this.donhong = donhong;
        this.donxinnghi = donxinnghi;
        this.thuong = thuong;
        this.phat = phat;
        this.tongluong = tongluong;
    }

    public int getMabl() {
        return mabl;
    }

    public void setMabl(int mabl) {
        this.mabl = mabl;
    }

    public String getMatx() {
        return matx;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getDonht() {
        return donht;
    }

    public void setDonht(int donht) {
        this.donht = donht;
    }

    public int getDonch() {
        return donch;
    }

    public void setDonch(int donch) {
        this.donch = donch;
    }

    public int getDonhong() {
        return donhong;
    }

    public void setDonhong(int donhong) {
        this.donhong = donhong;
    }

    public int getDonxinnghi() {
        return donxinnghi;
    }

    public void setDonxinnghi(int donxinnghi) {
        this.donxinnghi = donxinnghi;
    }

    public double getThuong() {
        return thuong;
    }

    public void setThuong(double thuong) {
        this.thuong = thuong;
    }

    public double getPhat() {
        return phat;
    }

    public void setPhat(double phat) {
        this.phat = phat;
    }

    public double getTongluong() {
        return tongluong;
    }

    public void setTongluong(double tongluong) {
        this.tongluong = tongluong;
    }

    
    
}
