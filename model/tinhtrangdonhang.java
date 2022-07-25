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
public class tinhtrangdonhang {

    private String mattdh;
    private String tenttdh;
    public tinhtrangdonhang() {

    }

    public tinhtrangdonhang(String mattdh, String tenttdh) {
        this.mattdh = mattdh;
        this.tenttdh = tenttdh; 
    }

    public String getMattdh() {
        return mattdh;
    }

    public void setMattdh(String mattdh) {
        this.mattdh = mattdh;
    }

    public String getTenttdh() {
        return tenttdh;
    }

    public void setTenttdh(String tenttdh) {
        this.tenttdh = tenttdh;
    }

}
