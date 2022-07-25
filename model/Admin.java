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
public class Admin extends Preson {
    private String maad;
    public Admin(){
    }

    public Admin(String maad, String ten, String gt, int tuoi, String cccd, String sdt, String diachi, String user) {
        super(ten, gt, tuoi, cccd, sdt, diachi, user);//tham chieu lop cha gan nhat
        this.maad = maad;
    }

    public String getMaad() {
        return maad;
    }

    public void setMaad(String maad) {
        this.maad = maad;
    }
    
}
