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
public class thongkebaocao {
    private int mabc;
    private Date time;
    private int tdht;
    private int tdch;
    private int tdhh;
    private int tddg;
    private double tongdoanhthu;
    private double quyluongsp;
    private double doanhthu;

    public thongkebaocao(){
        
    }
    
    public thongkebaocao(int mabc, Date time, int tdht, int tdch, int tdhh,int tddg, double tongdoanhthu, double quyluongsp,double doanhthu) {
        this.mabc = mabc;
        this.time = time;
        this.tdht = tdht;
        this.tdch = tdch;
        this.tdhh = tdhh;
        this.tddg = tddg;
        this.tongdoanhthu = tongdoanhthu;
        this.quyluongsp = quyluongsp;
        this.doanhthu = doanhthu;
    }

    public thongkebaocao(Date time, int tdht, int tdch, int tdhh,int tddg, double tongdoanhthu, double quyluongsp, double phihh, double doanhthu) {
        this.time = time;
        this.tdht = tdht;
        this.tdch = tdch;
        this.tdhh = tdhh;
        this.tongdoanhthu = tongdoanhthu;
        this.quyluongsp = quyluongsp;
        this.doanhthu = doanhthu;
    }

    public int getMabc() {
        return mabc;
    }

    public void setMabc(int mabc) {
        this.mabc = mabc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTdht() {
        return tdht;
    }

    public void setTdht(int tdht) {
        this.tdht = tdht;
    }

    public int getTdch() {
        return tdch;
    }

    public void setTdch(int tdch) {
        this.tdch = tdch;
    }

    public int getTdhh() {
        return tdhh;
    }

    public void setTdhh(int tdhh) {
        this.tdhh = tdhh;
    }

    public int getTddg() {
        return tddg;
    }

    public void setTddg(int tddg) {
        this.tddg = tddg;
    }

    public double getTongdoanhthu() {
        return tongdoanhthu;
    }

    public void setTongdoanhthu(double tongdoanhthu) {
        this.tongdoanhthu = tongdoanhthu;
    }

    public double getQuyluongsp() {
        return quyluongsp;
    }

    public void setQuyluongsp(double quyluongsp) {
        this.quyluongsp = quyluongsp;
    }

    public double getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(double doanhthu) {
        this.doanhthu = doanhthu;
    }
    
    
    
}
