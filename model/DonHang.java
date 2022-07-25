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
public class DonHang {
    private int madh;
    private String matx;
    private int maloaihh;
    private String makh;
    private String diachigiao;
    private String mattdh;
    private java.sql.Date tggiao;
    private double giatrihh;
    private float sao;
    public DonHang(){
    }

   
    public DonHang(String matx, int maloaihh, String makh, String diachigiao, String mattdh, java.sql.Date tggiao, double giatrihh, float sao) {
        this.matx = matx;
        this.maloaihh = maloaihh;
        this.makh = makh;
        this.diachigiao = diachigiao;
        this.mattdh = mattdh;
        this.tggiao = tggiao;
        this.giatrihh = giatrihh;
        this.sao = sao;
    }

    public DonHang(int madh, String matx, int maloaihh, String makh, String diachigiao, String mattdh, java.sql.Date tggiao, double giatrihh, float sao) {
        this.madh = madh;
        this.matx = matx;
        this.maloaihh = maloaihh;
        this.makh = makh;
        this.diachigiao = diachigiao;
        this.mattdh = mattdh;
        this.tggiao = tggiao;
        this.giatrihh = giatrihh;
        this.sao = sao;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public String getMatx() {
        return matx;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public int getMaloaihh() {
        return maloaihh;
    }

    public void setMaloaihh(int maloaihh) {
        this.maloaihh = maloaihh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getDiachigiao() {
        return diachigiao;
    }

    public void setDiachigiao(String diachigiao) {
        this.diachigiao = diachigiao;
    }

    public String getMattdh() {
        return mattdh;
    }

    public void setMsttdh(String mattdh) {
        this.mattdh = mattdh;
    }

    public java.sql.Date getTggiao() {
        return tggiao;
    }

    public void setTggiao(java.sql.Date tggiao) {
        this.tggiao = tggiao;
    }

    public double getGiatrihh() {
        return giatrihh;
    }

    public void setGiatrihh(double giatrihh) {
        this.giatrihh = giatrihh;
    }

    public float getSao() {
        return sao;
    }

    public void setSao(float sao) {
        this.sao = sao;
    }

    

    
}
