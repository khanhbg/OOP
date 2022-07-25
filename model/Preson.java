/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author PhanKhanh
 */
public class Preson {
    //thuoctinh
    private String ten;
    private String gt;
    private int tuoi;
    private String  cccd;
    private String sdt;
    private String diachi;
    private String user;
    //phuongthuc
    public Preson(){
    }
    public Preson(String ten, String gt, int tuoi, String cccd, String sdt, String diachi, String user) {
        this.ten = ten;
        this.gt = gt;
        this.tuoi = tuoi;
        this.cccd = cccd;
        this.sdt = sdt;
        this.diachi = diachi;
        this.user = user;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

   
    
}
