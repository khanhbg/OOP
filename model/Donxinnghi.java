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
import java.sql.Date;
public class Donxinnghi {
    private int madxn;
    private String matx;
    private Date time;

    public Donxinnghi(){
        
    }
    public Donxinnghi(String matx, Date time) {
        this.matx = matx;
        this.time = time;
    }

    public Donxinnghi(int madxn, String matx, Date time) {
        this.madxn = madxn;
        this.matx = matx;
        this.time = time;
    }

    public int getMadxn() {
        return madxn;
    }

    public void setMadxn(int madxn) {
        this.madxn = madxn;
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
    
    
    
}
