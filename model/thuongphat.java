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
public class thuongphat {

    private String matp;
    private String tentp;
    private double sotien;

    public thuongphat() {

    }

    public thuongphat(String matp, String tentp, double sotien) {
        this.matp = matp;
        this.tentp = tentp;
        this.sotien = sotien;
    }

    public String getMatp() {
        return matp;
    }

    public void setMatp(String matp) {
        this.matp = matp;
    }

    public String getTentp() {
        return tentp;
    }

    public void setTentp(String tentp) {
        this.tentp = tentp;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

}
