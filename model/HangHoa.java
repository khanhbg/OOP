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
public class HangHoa {

    private int maloaihh;
    private String tenhh;
    private double phiship;

    public HangHoa() {

    }

    public HangHoa(String tenhh, double phiship) {
        this.tenhh = tenhh;
        this.phiship = phiship;
    }

    public HangHoa(int maloaihh, String tenhh, double phiship) {
        this.maloaihh = maloaihh;
        this.tenhh = tenhh;
        this.phiship = phiship;
    }

    public int getMaloaihh() {
        return maloaihh;
    }

    public void setMaloaihh(int maloaihh) {
        this.maloaihh = maloaihh;
    }

    public String getTenhh() {
        return tenhh;
    }

    public void setTenhh(String tenhh) {
        this.tenhh = tenhh;
    }

    public double getPhiship() {
        return phiship;
    }

    public void setPhiship(double phiship) {
        this.phiship = phiship;
    }

}
