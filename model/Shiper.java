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
public class Shiper extends Preson {
    //thuoc tinh
    private String matx;
    private double luongcb;
    private float sao;
    private String bienxe;

    //phuong thuc
    //khoi tao khong doi so
    public Shiper() {
    }
    
    //khoi tao co doi so

    public Shiper(String matx, double luongcb, float sao, String bienxe, String ten, String gt, int tuoi, String cccd, String sdt, String diachi, String user) {
        super(ten, gt, tuoi, cccd, sdt, diachi, user);
        this.matx = matx;
        this.luongcb = luongcb;
        this.sao = sao;
        this.bienxe = bienxe;
    }

    

    public String getMatx() {
        return matx;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public double getLuongcb() {
        return luongcb;
    }

    public void setLuongcb(double luongcb) {
        this.luongcb = luongcb;
    }

    public float getSao() {
        return sao;
    }

    public void setSao(float sao) {
        this.sao = sao;
    }

    public String getBienxe() {
        return bienxe;
    }

    public void setBienxe(String bienxe) {
        this.bienxe = bienxe;
    }
    public static void main(String[] args) {
      
    }
}
