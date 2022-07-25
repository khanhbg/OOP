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
public class KhachHang extends Preson {

    private String makh;

    public KhachHang() {
    }

    public KhachHang(String makh, String ten, String gt, int tuoi, String cccd, String sdt, String diachi, String user) {
        super(ten, gt, tuoi, cccd, sdt, diachi, user);
        this.makh = makh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

}
