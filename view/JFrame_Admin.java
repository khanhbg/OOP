/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CT_Admin;
import controller.CT_BangLuong;
import controller.Mainq;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Shiper;
import model.DonHang;
import controller.CT_Shiper;
import controller.CT_DonHang;
import controller.CT_Donxinnghi;
import controller.CT_HangHoa;
import controller.CT_KhachHang;
import controller.CT_TKDangNhap;
import controller.CT_Thuongphat;
import controller.CT_TinhLuong;
import controller.CT_thongkebaocao;
import controller.CT_tinhtrangdonhang;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import model.KhachHang;
import model.TKDangNhap;
import java.util.Date;
import model.Admin;
import model.HangHoa;
import model.bangluong;
import model.thongkebaocao;
import model.thuongphat;
import view.JFrame_DangNhap;

/**
 *
 * @author PhanKhanh
 */
public class JFrame_Admin extends javax.swing.JFrame {

    /**
     * Creates new form JFrame_QliShipper
     */
    private String userrr;
    List<DonHang> donhangList = new ArrayList<>();
    DefaultTableModel table_dh;

    List<Shiper> shiperList = new ArrayList<>();
    DefaultTableModel table_sp;

    List<KhachHang> khachhangList = new ArrayList<>();
    DefaultTableModel table_kh;

    List<thuongphat> thuongphatList = new ArrayList<>();
    DefaultTableModel table_tp;

    List<HangHoa> hanghoaList = new ArrayList<>();
    DefaultTableModel table_hh;

    List<bangluong> bangluongList = new ArrayList<>();
    DefaultTableModel table_bl;

    List<thongkebaocao> tkbcList = new ArrayList<>();
    DefaultTableModel table_tkbc;
    
    public JFrame_Admin(String userr) {
        initComponents();
        jYear_tkbc.setYear(2021);
        table_sp = (DefaultTableModel) tbl_sp.getModel();
        showShiper();
        table_dh = (DefaultTableModel) tbl_dh.getModel();
        showDonhang();
        table_kh = (DefaultTableModel) tbl_kh.getModel();
        showKhachhang();
        table_tp = (DefaultTableModel) tbl_thuongphat.getModel();
        showThuongphat();
        table_hh = (DefaultTableModel) tbl_hh.getModel();
        showHanghoa();
        table_bl = (DefaultTableModel) tbl_bl.getModel();
        showBangluong();
        table_tkbc = (DefaultTableModel) tbl_tkbc.getModel();
        showBaocao(jYear_tkbc.getYear());
        //set popmenu cho các table sp
        tbl_sp.setComponentPopupMenu(popupmenu_sp);
        tbl_dh.setComponentPopupMenu(popupmenu_suadh);
        tbl_kh.setComponentPopupMenu(popupmenu_suakh);
        tbl_thuongphat.setComponentPopupMenu(popupmenu_suathuongphat);
        tbl_hh.setComponentPopupMenu(popupmenu_suaphiship);
        bt_tkh_nam.setSelected(true);
        bt_tsp_nam.setSelected(true);
        setsuaad(userr);
        showcbb();
    }

    //show shiper
    private void showShiper() {
        shiperList = CT_Shiper.findAll();
        table_sp.setRowCount(0);
        for (Shiper sp : shiperList) {
            table_sp.addRow(new Object[]{sp.getMatx(), sp.getTen(), sp.getGt(),
                sp.getTuoi(), sp.getBienxe(), sp.getCccd(), sp.getSdt(), sp.getDiachi(),
                sp.getLuongcb(), sp.getSao(), sp.getUser()});
        }
    }

    private void showDonhang() {
        donhangList = CT_DonHang.findAll();
        table_dh.setRowCount(0);
        for (DonHang dh : donhangList) {
            table_dh.addRow(new Object[]{dh.getMadh(), dh.getMatx(), dh.getMaloaihh(),
                dh.getMakh(), dh.getDiachigiao(), dh.getMattdh(), dh.getTggiao(),
                dh.getGiatrihh(), dh.getSao()});
        }
    }

    private void showKhachhang() {
        khachhangList = CT_KhachHang.findAll();
        table_kh.setRowCount(0);
        for (KhachHang kh : khachhangList) {
            table_kh.addRow(new Object[]{kh.getMakh(), kh.getTen(), kh.getGt(),
                kh.getTuoi(), kh.getCccd(), kh.getSdt(), kh.getDiachi(),
                kh.getUser()});
        }
    }

    private void showThuongphat() {
        thuongphatList = CT_Thuongphat.findAll();
        table_tp.setRowCount(0);
        for (thuongphat tp : thuongphatList) {
            table_tp.addRow(new Object[]{tp.getMatp(), tp.getTentp(), tp.getSotien()});
        }
    }

    private void showHanghoa() {
        hanghoaList = CT_HangHoa.findAll();
        table_hh.setRowCount(0);
        for (HangHoa hh : hanghoaList) {
            table_hh.addRow(new Object[]{hh.getMaloaihh(), hh.getTenhh(), hh.getPhiship()});
        }
    }

    private void showBangluong() {
        bangluongList = CT_BangLuong.findAll();
        table_bl.setRowCount(0);
        for (bangluong bl : bangluongList) {
            table_bl.addRow(new Object[]{bl.getMabl(), bl.getMatx(), bl.getDonht(), bl.getDonch(),
                bl.getDonhong(), bl.getDonxinnghi(), bl.getThuong(), bl.getPhat(), bl.getTongluong(), bl.getTime()});
        }
    }

    private void showBaocao(int nam) {
        tkbcList = CT_thongkebaocao.findtime(nam);
        table_tkbc.setRowCount(0);
        for (thongkebaocao tkbc : tkbcList) {
            table_tkbc.addRow(new Object[]{tkbc.getTime(), tkbc.getTdht(),
                tkbc.getTdch(), tkbc.getTdhh(), tkbc.getTddg(), tkbc.getTongdoanhthu(), tkbc.getQuyluongsp(),
                 tkbc.getDoanhthu()});
        }
    }

    //do du lieu len form sua?
    public void setsuasp(String matx) {
        Shiper sp = CT_Shiper.findMatx(matx);
        txt_ssp_ma.setText(sp.getMatx());
        txt_ssp_ten.setText(sp.getTen());
        txt_ssp_tuoi.setText(String.valueOf(sp.getTuoi()));
        txt_ssp_bienxe.setText(sp.getBienxe());
        txt_ssp_cccd.setText(sp.getCccd());
        txt_ssp_sdt.setText(sp.getSdt());
        txt_ssp_dc.setText(sp.getDiachi());
        txt_ssp_luong.setText(String.valueOf(sp.getLuongcb()));
        txt_ssp_sao.setText(String.valueOf(sp.getSao()));
        txt_ssp_user.setText(sp.getUser());
        if (sp.getGt().equals("Nam")) {
            bt_suasp_Nam.setSelected(true);
        } else {
            bt_suasp_nu.setSelected(true);
        }
    }

    public void setsuadh(int madh) {
        //
        DonHang dh = CT_DonHang.findmadh(madh);
        txt_suadh_madh.setText(String.valueOf(madh));
        int maloaihh = dh.getMaloaihh();
        txt_suadh_maloaihh.setText(String.valueOf(maloaihh));
        txt_suadh_tenhh.setText(CT_HangHoa.findMaloaihh(maloaihh).getTenhh());
        String mattdh = dh.getMattdh();
        txt_suadh_mattdh.setText(mattdh);
        txt_suadh_tenttdh.setText(CT_tinhtrangdonhang.findmattdh(mattdh).getTenttdh());
        jDate_suadh_time.setDate(dh.getTggiao());
        txt_suadh_dc.setText(dh.getDiachigiao());
        txt_suadh_giatrihh.setText(String.valueOf(dh.getGiatrihh()));
        txt_suadh_sao.setText(String.valueOf(dh.getSao()));
        String matx = dh.getMatx();
        txt_suadh_matx.setText(matx);
        txt_suadh_tensp.setText(CT_Shiper.findMatx(matx).getTen());
        txt_suadh_sdtsp.setText(CT_Shiper.findMatx(matx).getSdt());
        String makh = dh.getMakh();
        txt_suadh_makh.setText(makh);
        txt_suadh_tenkh.setText(CT_KhachHang.findmakh(makh).getTen());
        txt_suadh_sodtkh.setText(CT_KhachHang.findmakh(makh).getSdt());
    }

    public void setsuakh(String makh) {
        KhachHang kh = CT_KhachHang.findmakh(makh);
        txt_suakh_makh.setText(kh.getMakh());
        txt_suakh_tenkh.setText(kh.getTen());
        txt_suakh_tuoi.setText(String.valueOf(kh.getTuoi()));
        txt_suakh_cccd.setText(kh.getCccd());
        txt_suakh_dc.setText(kh.getDiachi());
        txt_suakh_sdt.setText(kh.getSdt());
        txt_suakh_user.setText(kh.getUser());
        String gt = kh.getGt();
        if (gt.equals("Nam")) {
            bt_suakh_nam.isSelected();
        } else {
            bt_suakh_nu.isSelected();
        }
    }
    public void setsuaad(String user) {
        Admin ad = CT_Admin.finduser(user);
        txt_suaad_ma.setText(ad.getMaad());
        txt_suaad_ten.setText(ad.getTen());
        txt_suaad_tuoi1.setText(String.valueOf(ad.getTuoi()));
        txt_suaad_cccd1.setText(ad.getCccd());
        txt_suaad_dc1.setText(ad.getDiachi());
        txt_suaad_sdt1.setText(ad.getSdt());
        txt_suaad_user1.setText(ad.getUser());
        String gt = ad.getGt();
        if (gt.equals("Nam")) {
            bt_suaad_nam1.isSelected();
        } else {
            bt_suaad_nu1.isSelected();
        }
    }
    public  void setsuahh(int maloaihh){
       HangHoa hh= CT_HangHoa.findMaloaihh(maloaihh);
       txt_suahh_ma.setText(String.valueOf(hh.getMaloaihh()));
       txt_suahh_ten.setText(hh.getTenhh());
       txt_suahh_ps.setText(String.valueOf(hh.getPhiship()));
               
    }

    public void showcbb() {
        List<Shiper> spList = new ArrayList<>();
        spList = CT_Shiper.findAll();
        for (Shiper sp : spList) {
            cbb_tdh_masp.addItem(sp.getMatx());
        }
        List<HangHoa> hhList = new ArrayList<>();
        hhList = CT_HangHoa.findAll();
        for (HangHoa hh : hhList) {
            cbb_tdh_maloaihh.addItem(String.valueOf(hh.getMaloaihh()));
        }
        List<KhachHang> khList = new ArrayList<>();
        khList = CT_KhachHang.findAll();
        for (KhachHang kh : khList) {
            cbb_tdh_makh.addItem(kh.getMakh());
        }
    }

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_tsp = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txt_tsp_user = new javax.swing.JTextField();
        txt_tsp_diachi = new javax.swing.JTextField();
        txt_tsp_matx = new javax.swing.JTextField();
        txt_tsp_ten = new javax.swing.JTextField();
        txt_tsp_sdt = new javax.swing.JTextField();
        txt_tsp_luong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_tsp_bienxe = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_tsp_tuoi = new javax.swing.JTextField();
        txt_tsp_cccd = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        bt_tsp_nam = new javax.swing.JRadioButton();
        bt_tsp_nu = new javax.swing.JRadioButton();
        btn_tsp_them = new javax.swing.JButton();
        btn_tsp_nhaplai = new javax.swing.JButton();
        btn_tsp_back = new javax.swing.JButton();
        jDialog_tdh = new javax.swing.JDialog();
        jPanel_gdthemdh = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txt_tdh_tensp = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        cbb_tdh_masp = new javax.swing.JComboBox<>();
        cbb_tdh_maloaihh = new javax.swing.JComboBox<>();
        jLabel95 = new javax.swing.JLabel();
        txt_tdh_phiship = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txt_tdh_tenmaloaihh1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jDate_tdh_time = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        txt_tdh_giatrihh = new javax.swing.JTextField();
        txt_tdh_tenkh = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        txt_tdh_diachi = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        cbb_tdh_makh = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btn_tdh_them = new javax.swing.JButton();
        btn_tdh_nhaplai = new javax.swing.JButton();
        btn_tdh_back = new javax.swing.JButton();
        buttonGr_baocao = new javax.swing.ButtonGroup();
        button_tsp = new javax.swing.ButtonGroup();
        jDialog_tkh = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txt_tkh_user = new javax.swing.JTextField();
        txt_tkh_diachi = new javax.swing.JTextField();
        txt_tkh_matx = new javax.swing.JTextField();
        txt_tkh_ten = new javax.swing.JTextField();
        txt_tkh_sdt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_tkh_tuoi = new javax.swing.JTextField();
        txt_tkh_cccd = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        bt_tkh_nam = new javax.swing.JRadioButton();
        bt_tkh_nu = new javax.swing.JRadioButton();
        btn_tkh_them = new javax.swing.JButton();
        btn_tsp_nhaplai1 = new javax.swing.JButton();
        btn_tsp_back1 = new javax.swing.JButton();
        btgr_tkh = new javax.swing.ButtonGroup();
        popupmenu_sp = new javax.swing.JPopupMenu();
        menuitem_sp_sua = new javax.swing.JMenuItem();
        menuitem_sp_xoa = new javax.swing.JMenuItem();
        jDialog_suasp = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        btn_jDia_suasp = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txt_ssp_ma = new javax.swing.JTextField();
        txt_ssp_ten = new javax.swing.JTextField();
        txt_ssp_tuoi = new javax.swing.JTextField();
        txt_ssp_bienxe = new javax.swing.JTextField();
        txt_ssp_sdt = new javax.swing.JTextField();
        txt_ssp_cccd = new javax.swing.JTextField();
        txt_ssp_dc = new javax.swing.JTextField();
        txt_ssp_luong = new javax.swing.JTextField();
        txt_ssp_sao = new javax.swing.JTextField();
        txt_ssp_user = new javax.swing.JTextField();
        bt_suasp_Nam = new javax.swing.JRadioButton();
        bt_suasp_nu = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        bt_suasp_nam = new javax.swing.JButton();
        bt_gr_suasp = new javax.swing.ButtonGroup();
        popupmenu_suadh = new javax.swing.JPopupMenu();
        menuitem_dh_sua = new javax.swing.JMenuItem();
        menuitem_dh_xoa = new javax.swing.JMenuItem();
        jDialog_suadh = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        txt_suadh_matx = new javax.swing.JTextField();
        txt_suadh_tensp = new javax.swing.JTextField();
        txt_suadh_sdtsp = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        txt_suadh_makh = new javax.swing.JTextField();
        txt_suadh_tenkh = new javax.swing.JTextField();
        txt_suadh_sodtkh = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txt_suadh_madh = new javax.swing.JTextField();
        txt_suadh_maloaihh = new javax.swing.JTextField();
        txt_suadh_tenhh = new javax.swing.JTextField();
        txt_suadh_giatrihh = new javax.swing.JTextField();
        txt_suadh_dc = new javax.swing.JTextField();
        txt_suadh_sao = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txt_suadh_mattdh = new javax.swing.JTextField();
        txt_suadh_tenttdh = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jDate_suadh_time = new com.toedter.calendar.JDateChooser();
        jPanel19 = new javax.swing.JPanel();
        bt_suadh_sua = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jDialog_suakh = new javax.swing.JDialog();
        jPanel27 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        txt_suakh_makh = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txt_suakh_tenkh = new javax.swing.JTextField();
        bt_suakh_nam = new javax.swing.JRadioButton();
        bt_suakh_nu = new javax.swing.JRadioButton();
        jLabel78 = new javax.swing.JLabel();
        txt_suakh_tuoi = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        btn_suakh_back = new javax.swing.JButton();
        btn_suakh_luu = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txt_suakh_cccd = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txt_suakh_sdt = new javax.swing.JTextField();
        txt_suakh_dc = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txt_suakh_user = new javax.swing.JTextField();
        jDialog_sthuongphat = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jDialog_sphiship = new javax.swing.JDialog();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        btn_suahh_back = new javax.swing.JButton();
        btn_suahh_lưu = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        txt_suahh_ma = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txt_suahh_ten = new javax.swing.JTextField();
        txt_suahh_ps = new javax.swing.JTextField();
        jPanel40 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        popupmenu_suakh = new javax.swing.JPopupMenu();
        menuitem_suakh_sua = new javax.swing.JMenuItem();
        menuitem_suakh_xoa = new javax.swing.JMenuItem();
        btgr_suakh = new javax.swing.ButtonGroup();
        popupmenu_suathuongphat = new javax.swing.JPopupMenu();
        menuitem_suatp = new javax.swing.JMenuItem();
        popupmenu_suaphiship = new javax.swing.JPopupMenu();
        menuitem_suaphiship = new javax.swing.JMenuItem();
        jDialog_suaadmin = new javax.swing.JDialog();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        txt_suaad_ma = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        txt_suaad_ten = new javax.swing.JTextField();
        bt_suaad_nam1 = new javax.swing.JRadioButton();
        bt_suaad_nu1 = new javax.swing.JRadioButton();
        jLabel100 = new javax.swing.JLabel();
        txt_suaad_tuoi1 = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        btn_suakh_back1 = new javax.swing.JButton();
        btn_suakh_luu1 = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        txt_suaad_cccd1 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        txt_suaad_sdt1 = new javax.swing.JTextField();
        txt_suaad_dc1 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txt_suaad_user1 = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        btn_dmk = new javax.swing.JButton();
        jDialog_doimk = new javax.swing.JDialog();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        btn_doimk = new javax.swing.JButton();
        jPassword_mkcu = new javax.swing.JPasswordField();
        jPasswordF_mkmoi = new javax.swing.JPasswordField();
        jPassword_mkmoi2 = new javax.swing.JPasswordField();
        jPanel_QLIPhanMem = new javax.swing.JPanel();
        jLb_tenpm = new javax.swing.JLabel();
        jPanel_chuatab = new javax.swing.JPanel();
        tab_admin1 = new javax.swing.JTabbedPane();
        jPanel_qlisp1 = new javax.swing.JPanel();
        jPanel_nhap = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_sp_matx = new javax.swing.JTextField();
        txt_sp_ten = new javax.swing.JTextField();
        txt_sp_sdt = new javax.swing.JTextField();
        btn_sp_nhaplai = new javax.swing.JButton();
        btn_sp_them = new javax.swing.JButton();
        btn_sp_timkiem = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txt_sp_bienxe = new javax.swing.JTextField();
        btn_sp_reset = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_sp = new javax.swing.JTable();
        jPanel_qlidh = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txt_dh_madh = new javax.swing.JTextField();
        txt_dh_matx = new javax.swing.JTextField();
        txt_dh_diachi = new javax.swing.JTextField();
        txt_dh_mattdh = new javax.swing.JTextField();
        btn_dh_nhaphang = new javax.swing.JButton();
        btn_dh_themdh = new javax.swing.JButton();
        btn_dh_timdh = new javax.swing.JButton();
        btn_dh_reset = new javax.swing.JButton();
        jDate_dh_time = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_dh = new javax.swing.JTable();
        jPanel_qlikh = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txt_kh_diachi = new javax.swing.JTextField();
        txt_kh_makh = new javax.swing.JTextField();
        txt_kh_tenkh = new javax.swing.JTextField();
        txt_kh_sdt = new javax.swing.JTextField();
        btn_kh_reset = new javax.swing.JButton();
        btn_kh_them = new javax.swing.JButton();
        btn_kh_timkiem = new javax.swing.JButton();
        btn_kh_nhaplai = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_kh = new javax.swing.JTable();
        jPanel_thuongphat = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thuongphat = new javax.swing.JTable();
        jPanel_hanghoa = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_hh = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        sfd = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel_nhaptimluong = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        txt_bl_matx = new javax.swing.JTextField();
        btn_bl_reset = new javax.swing.JButton();
        btn_bl_tinhluong = new javax.swing.JButton();
        btn_bl_tim = new javax.swing.JButton();
        jMonth_bl_thang = new com.toedter.calendar.JMonthChooser();
        jYear_bl_nam = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_bl_mabl = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbl_bl = new javax.swing.JTable();
        jPanel_baocao = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbl_tkbc = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        tkbc_rs = new javax.swing.JButton();
        jYear_tkbc = new com.toedter.calendar.JYearChooser();
        jButton5 = new javax.swing.JButton();

        jDialog_tsp.setMinimumSize(new java.awt.Dimension(800, 350));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 25)); // NOI18N
        jLabel5.setText("Thêm Shipper");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(301, 301, 301))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_tsp_user.setName(""); // NOI18N
        txt_tsp_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tsp_userActionPerformed(evt);
            }
        });

        txt_tsp_diachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tsp_diachiActionPerformed(evt);
            }
        });

        txt_tsp_matx.setName(""); // NOI18N
        txt_tsp_matx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tsp_matxActionPerformed(evt);
            }
        });

        txt_tsp_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tsp_tenActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel6.setText("Tên");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel8.setText("Tuổi");
        jLabel8.setAlignmentY(0.6F);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel20.setText("Biển số xe");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel9.setText("CCCD");

        txt_tsp_bienxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tsp_bienxeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel10.setText("SDT");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel11.setText("Địa chỉ");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel28.setText("Lương cơ bản");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel34.setText("User");

        txt_tsp_tuoi.setName(""); // NOI18N
        txt_tsp_tuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tsp_tuoiActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel35.setText("Mã tx");

        button_tsp.add(bt_tsp_nam);
        bt_tsp_nam.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_tsp_nam.setText("Nam");

        button_tsp.add(bt_tsp_nu);
        bt_tsp_nu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_tsp_nu.setText("Nữ");
        bt_tsp_nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tsp_nuActionPerformed(evt);
            }
        });

        btn_tsp_them.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tsp_them.setText("Thêm");
        btn_tsp_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tsp_themActionPerformed(evt);
            }
        });

        btn_tsp_nhaplai.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tsp_nhaplai.setText("Nhập Lại");
        btn_tsp_nhaplai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tsp_nhaplaiActionPerformed(evt);
            }
        });

        btn_tsp_back.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tsp_back.setText("Back");
        btn_tsp_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tsp_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(txt_tsp_bienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tsp_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(bt_tsp_nam)
                                        .addGap(38, 38, 38)
                                        .addComponent(bt_tsp_nu)
                                        .addGap(90, 90, 90))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tsp_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tsp_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28))
                                        .addGap(63, 63, 63)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tsp_user, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tsp_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tsp_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_tsp_back, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tsp_them, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222)
                        .addComponent(btn_tsp_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tsp_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tsp_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tsp_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tsp_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tsp_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_tsp_nam)
                    .addComponent(bt_tsp_nu)
                    .addComponent(txt_tsp_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(jLabel34)
                                .addComponent(jLabel11))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tsp_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tsp_user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tsp_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tsp_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tsp_bienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tsp_them, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tsp_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tsp_back, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_tspLayout = new javax.swing.GroupLayout(jDialog_tsp.getContentPane());
        jDialog_tsp.getContentPane().setLayout(jDialog_tspLayout);
        jDialog_tspLayout.setHorizontalGroup(
            jDialog_tspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog_tspLayout.setVerticalGroup(
            jDialog_tspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog_tdh.setMinimumSize(new java.awt.Dimension(800, 370));

        jPanel_gdthemdh.setMinimumSize(new java.awt.Dimension(650, 360));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel36.setText("Mã Shipper");

        txt_tdh_tensp.setEditable(false);
        txt_tdh_tensp.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_tdh_tensp.setName(""); // NOI18N
        txt_tdh_tensp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tdh_tenspActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel37.setText("Tên loại HH");
        jLabel37.setAlignmentY(0.6F);

        jLabel89.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel89.setText("Mã loại HH");
        jLabel89.setAlignmentY(0.6F);

        cbb_tdh_masp.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cbb_tdh_masp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tdh_maspActionPerformed(evt);
            }
        });

        cbb_tdh_maloaihh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cbb_tdh_maloaihh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tdh_maloaihhActionPerformed(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel95.setText("Tên Shipper");

        txt_tdh_phiship.setEditable(false);
        txt_tdh_phiship.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_tdh_phiship.setName(""); // NOI18N
        txt_tdh_phiship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tdh_phishipActionPerformed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel97.setText("Phí ship");
        jLabel97.setAlignmentY(0.6F);

        txt_tdh_tenmaloaihh1.setEditable(false);
        txt_tdh_tenmaloaihh1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_tdh_tenmaloaihh1.setName(""); // NOI18N
        txt_tdh_tenmaloaihh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tdh_tenmaloaihh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(cbb_tdh_masp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tdh_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbb_tdh_maloaihh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tdh_tenmaloaihh1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addComponent(txt_tdh_phiship, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jLabel89)
                        .addComponent(jLabel95)
                        .addComponent(jLabel97))
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tdh_tensp)
                    .addComponent(cbb_tdh_maloaihh, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_tdh_tenmaloaihh1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txt_tdh_phiship, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(cbb_tdh_masp))
                .addContainerGap())
        );

        jLabel83.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel83.setText("Thời gian giao");

        jDate_tdh_time.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel41.setText("Giá trị hàng");

        txt_tdh_giatrihh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_tdh_giatrihh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tdh_giatrihhActionPerformed(evt);
            }
        });

        txt_tdh_tenkh.setEditable(false);
        txt_tdh_tenkh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel40.setText("Địa chỉ giao hàng");

        jLabel96.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel96.setText("Tên khách hàng");

        txt_tdh_diachi.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel39.setText("Mã KH");

        cbb_tdh_makh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cbb_tdh_makh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tdh_makhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(cbb_tdh_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tdh_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tdh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDate_tdh_time, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tdh_giatrihh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel96)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tdh_giatrihh)
                    .addComponent(jDate_tdh_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_tdh_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tdh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbb_tdh_makh))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 25)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Thêm đơn hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_tdh_them.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tdh_them.setText("Thêm");
        btn_tdh_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tdh_themActionPerformed(evt);
            }
        });

        btn_tdh_nhaplai.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tdh_nhaplai.setText("Nhập Lại");
        btn_tdh_nhaplai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tdh_nhaplaiActionPerformed(evt);
            }
        });

        btn_tdh_back.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tdh_back.setText("Back");
        btn_tdh_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tdh_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_tdh_back, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(btn_tdh_them, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(btn_tdh_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tdh_back, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tdh_them, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tdh_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_gdthemdhLayout = new javax.swing.GroupLayout(jPanel_gdthemdh);
        jPanel_gdthemdh.setLayout(jPanel_gdthemdhLayout);
        jPanel_gdthemdhLayout.setHorizontalGroup(
            jPanel_gdthemdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_gdthemdhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel_gdthemdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_gdthemdhLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel_gdthemdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel_gdthemdhLayout.setVerticalGroup(
            jPanel_gdthemdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_gdthemdhLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(jPanel_gdthemdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_gdthemdhLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(209, 209, 209)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jDialog_tdhLayout = new javax.swing.GroupLayout(jDialog_tdh.getContentPane());
        jDialog_tdh.getContentPane().setLayout(jDialog_tdhLayout);
        jDialog_tdhLayout.setHorizontalGroup(
            jDialog_tdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_tdhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_gdthemdh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog_tdhLayout.setVerticalGroup(
            jDialog_tdhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_tdhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_gdthemdh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 25)); // NOI18N
        jLabel7.setText("Thêm Khách hàng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(301, 301, 301))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_tkh_user.setName(""); // NOI18N
        txt_tkh_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tkh_userActionPerformed(evt);
            }
        });

        txt_tkh_diachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tkh_diachiActionPerformed(evt);
            }
        });

        txt_tkh_matx.setName(""); // NOI18N
        txt_tkh_matx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tkh_matxActionPerformed(evt);
            }
        });

        txt_tkh_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tkh_tenActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel12.setText("Tên khách hàng");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel13.setText("Tuổi");
        jLabel13.setAlignmentY(0.6F);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel16.setText("CCCD");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel17.setText("SDT");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel18.setText("Địa chỉ");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel38.setText("User");

        txt_tkh_tuoi.setName(""); // NOI18N
        txt_tkh_tuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tkh_tuoiActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel54.setText("Mã kh");

        btgr_tkh.add(bt_tkh_nam);
        bt_tkh_nam.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_tkh_nam.setText("Nam");

        button_tsp.add(bt_tkh_nu);
        bt_tkh_nu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_tkh_nu.setText("Nữ");
        bt_tkh_nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tkh_nuActionPerformed(evt);
            }
        });

        btn_tkh_them.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tkh_them.setText("Thêm");
        btn_tkh_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tkh_themActionPerformed(evt);
            }
        });

        btn_tsp_nhaplai1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tsp_nhaplai1.setText("Nhập Lại");
        btn_tsp_nhaplai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tsp_nhaplai1ActionPerformed(evt);
            }
        });

        btn_tsp_back1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_tsp_back1.setText("Back");
        btn_tsp_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tsp_back1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btn_tsp_back1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_tkh_them, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(213, 213, 213)
                                .addComponent(btn_tsp_nhaplai1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tkh_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(118, 118, 118)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tkh_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tkh_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(txt_tkh_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addComponent(txt_tkh_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(bt_tkh_nam)
                                            .addGap(18, 18, 18)
                                            .addComponent(bt_tkh_nu))
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_tkh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel18))
                                            .addGap(114, 114, 114)
                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel38)
                                                .addComponent(txt_tkh_user, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tkh_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tkh_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tkh_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_tkh_nam)
                    .addComponent(bt_tkh_nu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tkh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tkh_user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tkh_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tkh_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tkh_them, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tsp_nhaplai1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tsp_back1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_tkhLayout = new javax.swing.GroupLayout(jDialog_tkh.getContentPane());
        jDialog_tkh.getContentPane().setLayout(jDialog_tkhLayout);
        jDialog_tkhLayout.setHorizontalGroup(
            jDialog_tkhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog_tkhLayout.setVerticalGroup(
            jDialog_tkhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuitem_sp_sua.setText("Sửa");
        menuitem_sp_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_sp_suaActionPerformed(evt);
            }
        });
        popupmenu_sp.add(menuitem_sp_sua);

        menuitem_sp_xoa.setText("Xóa");
        menuitem_sp_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_sp_xoaActionPerformed(evt);
            }
        });
        popupmenu_sp.add(menuitem_sp_xoa);

        jDialog_suasp.setMinimumSize(new java.awt.Dimension(640, 670));

        btn_jDia_suasp.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_jDia_suasp.setText("Back");
        btn_jDia_suasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jDia_suaspActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Sửa Shipper");
        jLabel22.setToolTipText("");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_jDia_suasp, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_jDia_suasp, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_ssp_ma.setEditable(false);
        txt_ssp_ma.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_ssp_ten.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txt_ssp_tuoi.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_tuoi.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_ssp_bienxe.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_bienxe.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_ssp_sdt.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_sdt.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_ssp_cccd.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_cccd.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_ssp_dc.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_dc.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_ssp_luong.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_luong.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_ssp_luong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ssp_luongActionPerformed(evt);
            }
        });

        txt_ssp_sao.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_sao.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txt_ssp_user.setEditable(false);
        txt_ssp_user.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_ssp_user.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        bt_gr_suasp.add(bt_suasp_Nam);
        bt_suasp_Nam.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_suasp_Nam.setText("Nam");

        bt_gr_suasp.add(bt_suasp_nu);
        bt_suasp_nu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_suasp_nu.setText("Nữ");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel31.setText("Biển xe");

        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel56.setText("Sao");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel27.setText("Tên shipper");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel33.setText("Số ĐT");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel55.setText("Lương CB");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel23.setText("Mã shipper");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel32.setText("CCCD");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel29.setText("Giới tính");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel42.setText("Địa chỉ");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel30.setText("Tuổi");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel57.setText("User");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(bt_suasp_Nam)
                                .addGap(49, 49, 49)
                                .addComponent(bt_suasp_nu)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_ssp_cccd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                                    .addComponent(txt_ssp_bienxe, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_ssp_tuoi, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_ssp_ten, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_ssp_ma, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ssp_sao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                            .addComponent(txt_ssp_luong, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_ssp_dc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_ssp_sdt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_ssp_user))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ssp_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(txt_ssp_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(bt_suasp_Nam)
                    .addComponent(bt_suasp_nu))
                .addGap(12, 12, 12)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_bienxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_dc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_luong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_sao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ssp_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)))
        );

        bt_suasp_nam.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        bt_suasp_nam.setText("Lưu");
        bt_suasp_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suasp_namActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(bt_suasp_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_suasp_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog_suaspLayout = new javax.swing.GroupLayout(jDialog_suasp.getContentPane());
        jDialog_suasp.getContentPane().setLayout(jDialog_suaspLayout);
        jDialog_suaspLayout.setHorizontalGroup(
            jDialog_suaspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suaspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_suaspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialog_suaspLayout.setVerticalGroup(
            jDialog_suaspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suaspLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuitem_dh_sua.setText("Sửa");
        menuitem_dh_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_dh_suaActionPerformed(evt);
            }
        });
        popupmenu_suadh.add(menuitem_dh_sua);

        menuitem_dh_xoa.setText("Xóa");
        menuitem_dh_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_dh_xoaActionPerformed(evt);
            }
        });
        popupmenu_suadh.add(menuitem_dh_xoa);

        jDialog_suadh.setMinimumSize(new java.awt.Dimension(640, 620));
        jDialog_suadh.setPreferredSize(new java.awt.Dimension(660, 559));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel60.setText("Mã shipper");

        txt_suadh_matx.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_tensp.setEditable(false);
        txt_suadh_tensp.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_sdtsp.setEditable(false);
        txt_suadh_sdtsp.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel65.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel65.setText("Tên shipper");

        jLabel68.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel68.setText("Số điện thoại");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suadh_sdtsp)
                    .addComponent(txt_suadh_tensp)
                    .addComponent(txt_suadh_matx))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txt_suadh_matx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_sdtsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel58.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel58.setText("Mã khách hàng");

        txt_suadh_makh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_tenkh.setEditable(false);
        txt_suadh_tenkh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_sodtkh.setEditable(false);
        txt_suadh_sodtkh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_suadh_sodtkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_suadh_sodtkhActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel70.setText("Tên khách hàng");

        jLabel71.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel71.setText("Số điện thoại");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suadh_sodtkh, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txt_suadh_tenkh)
                    .addComponent(txt_suadh_makh))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(txt_suadh_makh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_sodtkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel63.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel63.setText("Mã đơn hàng");

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel67.setText("Mã loại hh");

        jLabel61.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel61.setText("Tên hàng hóa");

        jLabel62.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel62.setText("Giá trị hh");

        jLabel59.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel59.setText("Sao");

        jLabel64.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel64.setText("Địa chỉ giao");

        jLabel66.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel66.setText("Thời gian giao");

        txt_suadh_madh.setEditable(false);
        txt_suadh_madh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_maloaihh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_suadh_maloaihh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_suadh_maloaihhActionPerformed(evt);
            }
        });

        txt_suadh_tenhh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_giatrihh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_dc.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_sao.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel72.setText("Mã ttdh");

        txt_suadh_mattdh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suadh_tenttdh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        txt_suadh_tenttdh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_suadh_tenttdhActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel73.setText("Tên ttdh");

        jDate_suadh_time.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suadh_giatrihh, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_suadh_sao)
                    .addComponent(txt_suadh_dc)
                    .addComponent(txt_suadh_maloaihh, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_suadh_madh)
                    .addComponent(txt_suadh_tenttdh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(txt_suadh_mattdh)
                    .addComponent(txt_suadh_tenhh)
                    .addComponent(jDate_suadh_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_madh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_maloaihh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_tenhh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addGap(14, 14, 14)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_mattdh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_tenttdh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel66)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jDate_suadh_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txt_suadh_dc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suadh_giatrihh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txt_suadh_sao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bt_suadh_sua.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        bt_suadh_sua.setText("Lưu");
        bt_suadh_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suadh_suaActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_back.setText("Quay lại");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_suadh_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(bt_suadh_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        jLabel69.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Chi tiết đơn hàng");
        jLabel69.setToolTipText("");
        jLabel69.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_suadhLayout = new javax.swing.GroupLayout(jDialog_suadh.getContentPane());
        jDialog_suadh.getContentPane().setLayout(jDialog_suadhLayout);
        jDialog_suadhLayout.setHorizontalGroup(
            jDialog_suadhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suadhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_suadhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jDialog_suadhLayout.setVerticalGroup(
            jDialog_suadhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suadhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialog_suakh.setMinimumSize(new java.awt.Dimension(660, 380));

        jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Sửa thông tin khách hàng");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel75.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel75.setText("Mã khách hàng");

        txt_suakh_makh.setEditable(false);
        txt_suakh_makh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel76.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel76.setText("Tên khách hàng");

        txt_suakh_tenkh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        btgr_suakh.add(bt_suakh_nam);
        bt_suakh_nam.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_suakh_nam.setText("Nam");

        btgr_suakh.add(bt_suakh_nu);
        bt_suakh_nu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_suakh_nu.setText("Nữ");

        jLabel78.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel78.setText("Tuổi");

        txt_suakh_tuoi.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_suakh_nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_suakh_nu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_suakh_tenkh, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(txt_suakh_makh))
                            .addComponent(txt_suakh_tuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suakh_makh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suakh_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_suakh_nam)
                    .addComponent(bt_suakh_nu))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suakh_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_suakh_back.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_suakh_back.setText("Quay lại");
        btn_suakh_back.setToolTipText("");
        btn_suakh_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suakh_backActionPerformed(evt);
            }
        });

        btn_suakh_luu.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_suakh_luu.setText("Lưu");
        btn_suakh_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suakh_luuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_suakh_back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_suakh_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_suakh_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_suakh_luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel79.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel79.setText("CCCD");

        txt_suakh_cccd.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel80.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel80.setText("Số điện thoại");

        txt_suakh_sdt.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suakh_dc.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel84.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel84.setText("Địa chỉ");

        jLabel77.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel77.setText("User");

        txt_suakh_user.setEditable(false);
        txt_suakh_user.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_suakh_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_suakh_sdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_suakh_dc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_suakh_user, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suakh_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addGap(18, 18, 18)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suakh_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addGap(18, 18, 18)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suakh_dc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84))
                .addGap(18, 18, 18)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suakh_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog_suakhLayout = new javax.swing.GroupLayout(jDialog_suakh.getContentPane());
        jDialog_suakh.getContentPane().setLayout(jDialog_suakhLayout);
        jDialog_suakhLayout.setHorizontalGroup(
            jDialog_suakhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suakhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_suakhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialog_suakhLayout.setVerticalGroup(
            jDialog_suakhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suakhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog_sthuongphat.setMinimumSize(new java.awt.Dimension(450, 350));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Sửa mức thưởng phạt");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton3.setText("Quay lại");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton4.setText("Sửa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jLabel86.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel86.setText("Mã thưởng /phạt");

        jTextField23.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTextField23.setText("jTextField23");

        jLabel87.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel87.setText("Tên thưởng /phạt");

        jLabel88.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel88.setText("Số tiền");

        jTextField24.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTextField24.setText("jTextField23");

        jTextField25.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTextField25.setText("jTextField23");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(jTextField25)
                    .addComponent(jTextField23))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog_sthuongphatLayout = new javax.swing.GroupLayout(jDialog_sthuongphat.getContentPane());
        jDialog_sthuongphat.getContentPane().setLayout(jDialog_sthuongphatLayout);
        jDialog_sthuongphatLayout.setHorizontalGroup(
            jDialog_sthuongphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_sthuongphatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_sthuongphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialog_sthuongphatLayout.setVerticalGroup(
            jDialog_sthuongphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_sthuongphatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialog_sphiship.setMinimumSize(new java.awt.Dimension(450, 350));

        btn_suahh_back.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_suahh_back.setText("Quay lại");
        btn_suahh_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suahh_backActionPerformed(evt);
            }
        });

        btn_suahh_lưu.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_suahh_lưu.setText("Lưu");
        btn_suahh_lưu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suahh_lưuActionPerformed(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel91.setText("Mã loại HH");

        txt_suahh_ma.setEditable(false);
        txt_suahh_ma.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel92.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel92.setText("Tên loại HH");

        jLabel94.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel94.setText("Phí ship");

        txt_suahh_ten.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suahh_ps.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suahh_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(txt_suahh_ps)
                    .addComponent(txt_suahh_ma))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suahh_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91))
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(txt_suahh_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(txt_suahh_ps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_suahh_back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(btn_suahh_lưu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_suahh_back)
                    .addComponent(btn_suahh_lưu))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Sửa phí ship");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog_sphishipLayout = new javax.swing.GroupLayout(jDialog_sphiship.getContentPane());
        jDialog_sphiship.getContentPane().setLayout(jDialog_sphishipLayout);
        jDialog_sphishipLayout.setHorizontalGroup(
            jDialog_sphishipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_sphishipLayout.createSequentialGroup()
                .addGroup(jDialog_sphishipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_sphishipLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialog_sphishipLayout.setVerticalGroup(
            jDialog_sphishipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_sphishipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuitem_suakh_sua.setText("Sửa");
        menuitem_suakh_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_suakh_suaActionPerformed(evt);
            }
        });
        popupmenu_suakh.add(menuitem_suakh_sua);

        menuitem_suakh_xoa.setText("Xóa");
        menuitem_suakh_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_suakh_xoaActionPerformed(evt);
            }
        });
        popupmenu_suakh.add(menuitem_suakh_xoa);

        menuitem_suatp.setText("sửa");
        menuitem_suatp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_suatpActionPerformed(evt);
            }
        });
        popupmenu_suathuongphat.add(menuitem_suatp);

        menuitem_suaphiship.setText("Sửa");
        menuitem_suaphiship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitem_suaphishipActionPerformed(evt);
            }
        });
        popupmenu_suaphiship.add(menuitem_suaphiship);

        jDialog_suaadmin.setMinimumSize(new java.awt.Dimension(650, 390));

        jLabel98.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel98.setText("Mã admin");

        txt_suaad_ma.setEditable(false);
        txt_suaad_ma.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel99.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel99.setText("Tên ");

        txt_suaad_ten.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        btgr_suakh.add(bt_suaad_nam1);
        bt_suaad_nam1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_suaad_nam1.setText("Nam");

        btgr_suakh.add(bt_suaad_nu1);
        bt_suaad_nu1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        bt_suaad_nu1.setText("Nữ");

        jLabel100.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel100.setText("Tuổi");

        txt_suaad_tuoi1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_suaad_nam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_suaad_nu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_suaad_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(txt_suaad_ma))
                            .addComponent(txt_suaad_tuoi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suaad_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98))
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suaad_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99))
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_suaad_nam1)
                    .addComponent(bt_suaad_nu1))
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suaad_tuoi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel100))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_suakh_back1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_suakh_back1.setText("Quay lại");
        btn_suakh_back1.setToolTipText("");
        btn_suakh_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suakh_back1ActionPerformed(evt);
            }
        });

        btn_suakh_luu1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_suakh_luu1.setText("Lưu");
        btn_suakh_luu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suakh_luu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_suakh_back1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_suakh_luu1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_suakh_back1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_suakh_luu1))
                .addContainerGap())
        );

        jLabel101.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel101.setText("CCCD");

        txt_suaad_cccd1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel102.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel102.setText("Số điện thoại");

        txt_suaad_sdt1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        txt_suaad_dc1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel103.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel103.setText("Địa chỉ");

        jLabel104.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel104.setText("User");

        txt_suaad_user1.setEditable(false);
        txt_suaad_user1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel47Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_suaad_cccd1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel104, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_suaad_sdt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_suaad_dc1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_suaad_user1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_suaad_cccd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suaad_sdt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suaad_dc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_suaad_user1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("Cá nhân");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_dmk.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_dmk.setText("Đổi mật khẩu");
        btn_dmk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dmkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog_suaadminLayout = new javax.swing.GroupLayout(jDialog_suaadmin.getContentPane());
        jDialog_suaadmin.getContentPane().setLayout(jDialog_suaadminLayout);
        jDialog_suaadminLayout.setHorizontalGroup(
            jDialog_suaadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suaadminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_suaadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_suaadminLayout.createSequentialGroup()
                        .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_dmk))
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialog_suaadminLayout.setVerticalGroup(
            jDialog_suaadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_suaadminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_suaadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog_suaadminLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btn_dmk)))
                .addGap(18, 18, 18)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog_doimk.setMinimumSize(new java.awt.Dimension(380, 270));

        jLabel106.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel106.setText("Nhập mật khẩu cũ");

        jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel107.setText("Nhập mật khẩu mới");

        jLabel108.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel108.setText("Nhập lại mật khẩu mới");

        btn_doimk.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_doimk.setText("Lưu");
        btn_doimk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doimkActionPerformed(evt);
            }
        });

        jPassword_mkcu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jPasswordF_mkmoi.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jPassword_mkmoi2.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        javax.swing.GroupLayout jDialog_doimkLayout = new javax.swing.GroupLayout(jDialog_doimk.getContentPane());
        jDialog_doimk.getContentPane().setLayout(jDialog_doimkLayout);
        jDialog_doimkLayout.setHorizontalGroup(
            jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_doimkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel108)
                    .addComponent(jLabel107)
                    .addComponent(jLabel106))
                .addGap(10, 10, 10)
                .addGroup(jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordF_mkmoi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPassword_mkcu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPassword_mkmoi2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jDialog_doimkLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(btn_doimk, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jDialog_doimkLayout.setVerticalGroup(
            jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_doimkLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(jPassword_mkcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(jPasswordF_mkmoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jDialog_doimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(jPassword_mkmoi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btn_doimk)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame_GDChinhAD"); // NOI18N

        jLb_tenpm.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLb_tenpm.setText("                                                        PHẦN MỀM QUẢN LÍ SHIPPER");
        jLb_tenpm.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        tab_admin1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tab_admin1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel14.setText("Quản Lí Shipper");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel15.setText("Mã tx");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel19.setText("Tên");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel24.setText("SDT");

        txt_sp_matx.setName(""); // NOI18N
        txt_sp_matx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sp_matxActionPerformed(evt);
            }
        });

        txt_sp_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sp_tenActionPerformed(evt);
            }
        });

        btn_sp_nhaplai.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_sp_nhaplai.setText("Nhập Lại");
        btn_sp_nhaplai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sp_nhaplaiActionPerformed(evt);
            }
        });

        btn_sp_them.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_sp_them.setText("Thêm");
        btn_sp_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sp_themActionPerformed(evt);
            }
        });

        btn_sp_timkiem.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_sp_timkiem.setText("Tìm Kiếm");
        btn_sp_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sp_timkiemActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel25.setText("Biển số xe");

        txt_sp_bienxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sp_bienxeActionPerformed(evt);
            }
        });

        btn_sp_reset.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_sp_reset.setText("Reset");
        btn_sp_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sp_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_nhapLayout = new javax.swing.GroupLayout(jPanel_nhap);
        jPanel_nhap.setLayout(jPanel_nhapLayout);
        jPanel_nhapLayout.setHorizontalGroup(
            jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_nhapLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel_nhapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel_nhapLayout.createSequentialGroup()
                        .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sp_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sp_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sp_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(166, 166, 166)
                        .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txt_sp_bienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_nhapLayout.createSequentialGroup()
                        .addComponent(btn_sp_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_sp_them, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(btn_sp_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(btn_sp_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel_nhapLayout.setVerticalGroup(
            jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_nhapLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel15)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sp_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sp_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sp_bienxe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sp_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel_nhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_sp_them, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sp_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sp_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sp_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl_sp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tx", "Tên", "Giới tính", "Tuổi", "Biển xe", "CCCD", "SDT", "Địa chỉ", "LươngCB", "Sao", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(tbl_sp);
        if (tbl_sp.getColumnModel().getColumnCount() > 0) {
            tbl_sp.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbl_sp.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbl_sp.getColumnModel().getColumn(3).setPreferredWidth(30);
            tbl_sp.getColumnModel().getColumn(9).setPreferredWidth(30);
            tbl_sp.getColumnModel().getColumn(10).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel_qlisp1Layout = new javax.swing.GroupLayout(jPanel_qlisp1);
        jPanel_qlisp1.setLayout(jPanel_qlisp1Layout);
        jPanel_qlisp1Layout.setHorizontalGroup(
            jPanel_qlisp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_qlisp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_qlisp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_nhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel_qlisp1Layout.setVerticalGroup(
            jPanel_qlisp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_qlisp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_nhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_admin1.addTab("Quản lí shipper", jPanel_qlisp1);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel26.setText("Quản Lí Đơn Hàng");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel44.setText("Mã ĐH");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel45.setText("Mã Tài xế");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel46.setText("Địa chỉ giao hàng");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel47.setText("Mã ttdh");

        txt_dh_madh.setName(""); // NOI18N
        txt_dh_madh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dh_madhActionPerformed(evt);
            }
        });

        btn_dh_nhaphang.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_dh_nhaphang.setText("Nhập Lại");
        btn_dh_nhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dh_nhaphangActionPerformed(evt);
            }
        });

        btn_dh_themdh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_dh_themdh.setText("Tạo đơn");
        btn_dh_themdh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dh_themdhActionPerformed(evt);
            }
        });

        btn_dh_timdh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_dh_timdh.setText("Tìm Kiếm");
        btn_dh_timdh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dh_timdhActionPerformed(evt);
            }
        });

        btn_dh_reset.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_dh_reset.setText("Reset");
        btn_dh_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dh_resetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Từ ngày");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel2.setText("Đến ngày");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addComponent(btn_dh_timdh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_dh_themdh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDate_dh_time, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_dh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel44)
                                        .addGap(24, 24, 24))
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(txt_dh_madh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btn_dh_nhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addComponent(btn_dh_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dh_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(txt_dh_mattdh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dh_mattdh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dh_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel44)
                                .addComponent(jLabel45)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_dh_madh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jDate_dh_time, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(txt_dh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dh_nhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dh_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dh_themdh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dh_timdh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl_dh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbl_dh.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        tbl_dh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ĐH", "Mã Tài xế", "Mã Loại HH", "Mã KH", "Địa chỉ", "Mã TTDH", "Thời Gian Tạo", "Gía trị hàng", "Sao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_dh.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane10.setViewportView(tbl_dh);
        tbl_dh.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel_qlidhLayout = new javax.swing.GroupLayout(jPanel_qlidh);
        jPanel_qlidh.setLayout(jPanel_qlidhLayout);
        jPanel_qlidhLayout.setHorizontalGroup(
            jPanel_qlidhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_qlidhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_qlidhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_qlidhLayout.setVerticalGroup(
            jPanel_qlidhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_qlidhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab_admin1.addTab("Quản lí đơn hàng", jPanel_qlidh);

        jPanel_qlikh.setPreferredSize(new java.awt.Dimension(700, 445));

        jLabel48.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel48.setText("Quản Lí Khách Hàng");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel49.setText("Mã KH");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel50.setText("Tên KH");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel51.setText("SĐT");

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel52.setText("Địa chỉ");

        txt_kh_makh.setName(""); // NOI18N
        txt_kh_makh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kh_makhActionPerformed(evt);
            }
        });

        btn_kh_reset.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_kh_reset.setText("Reset");
        btn_kh_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kh_resetActionPerformed(evt);
            }
        });

        btn_kh_them.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_kh_them.setText("Thêm mới");
        btn_kh_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kh_themActionPerformed(evt);
            }
        });

        btn_kh_timkiem.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_kh_timkiem.setText("Tìm Kiếm");
        btn_kh_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kh_timkiemActionPerformed(evt);
            }
        });

        btn_kh_nhaplai.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_kh_nhaplai.setText("Nhập Lại");
        btn_kh_nhaplai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kh_nhaplaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(btn_kh_them, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(btn_kh_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_kh_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51)
                                    .addGroup(jPanel26Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(btn_kh_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                        .addComponent(btn_kh_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(54, 54, 54))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(txt_kh_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kh_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(0, 46, Short.MAX_VALUE)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kh_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kh_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kh_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_kh_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kh_them, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kh_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kh_nhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl_kh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kh", "Tên kh", "Giới tính", "Tuổi", "CCCD", "SĐT", "Địa chỉ", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_kh.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane11.setViewportView(tbl_kh);
        if (tbl_kh.getColumnModel().getColumnCount() > 0) {
            tbl_kh.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel_qlikhLayout = new javax.swing.GroupLayout(jPanel_qlikh);
        jPanel_qlikh.setLayout(jPanel_qlikhLayout);
        jPanel_qlikhLayout.setHorizontalGroup(
            jPanel_qlikhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_qlikhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_qlikhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane11))
                .addContainerGap())
        );
        jPanel_qlikhLayout.setVerticalGroup(
            jPanel_qlikhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_qlikhLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tab_admin1.addTab("Quản khách hàng", jPanel_qlikh);

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel53.setText("Quản lí thưởng phạt");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_thuongphat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thưởng phạt", "Tên thưởng phạt", "Số tiền"
            }
        ));
        jScrollPane1.setViewportView(tbl_thuongphat);

        javax.swing.GroupLayout jPanel_thuongphatLayout = new javax.swing.GroupLayout(jPanel_thuongphat);
        jPanel_thuongphat.setLayout(jPanel_thuongphatLayout);
        jPanel_thuongphatLayout.setHorizontalGroup(
            jPanel_thuongphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_thuongphatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_thuongphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_thuongphatLayout.setVerticalGroup(
            jPanel_thuongphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_thuongphatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_admin1.addTab("Quản lí thưởng phạt", jPanel_thuongphat);

        tbl_hh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại hàng hóa", "Tên loại hàng hó", "Phí ship"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tbl_hh);
        if (tbl_hh.getColumnModel().getColumnCount() > 0) {
            tbl_hh.getColumnModel().getColumn(2).setResizable(false);
        }

        sfd.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        sfd.setText("Quản lí phí ship");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sfd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sfd, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_hanghoaLayout = new javax.swing.GroupLayout(jPanel_hanghoa);
        jPanel_hanghoa.setLayout(jPanel_hanghoaLayout);
        jPanel_hanghoaLayout.setHorizontalGroup(
            jPanel_hanghoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_hanghoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_hanghoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_hanghoaLayout.setVerticalGroup(
            jPanel_hanghoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_hanghoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_admin1.addTab("Quản lí phí ship", jPanel_hanghoa);

        jPanel31.setPreferredSize(new java.awt.Dimension(700, 445));

        jLabel81.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel81.setText("Quản Lí Lương Shipper");

        jLabel82.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel82.setText("Mã tx");

        txt_bl_matx.setName(""); // NOI18N
        txt_bl_matx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bl_matxActionPerformed(evt);
            }
        });

        btn_bl_reset.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_bl_reset.setText("Reset");
        btn_bl_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bl_resetActionPerformed(evt);
            }
        });

        btn_bl_tinhluong.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_bl_tinhluong.setText("Tính lương hiện tại");
        btn_bl_tinhluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bl_tinhluongActionPerformed(evt);
            }
        });

        btn_bl_tim.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btn_bl_tim.setText("Tìm Kiếm");
        btn_bl_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bl_timActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel3.setText("Tháng");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel4.setText("Năm");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel21.setText("Mã bl");

        javax.swing.GroupLayout jPanel_nhaptimluongLayout = new javax.swing.GroupLayout(jPanel_nhaptimluong);
        jPanel_nhaptimluong.setLayout(jPanel_nhaptimluongLayout);
        jPanel_nhaptimluongLayout.setHorizontalGroup(
            jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_nhaptimluongLayout.createSequentialGroup()
                .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_nhaptimluongLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_bl_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(btn_bl_tinhluong, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_nhaptimluongLayout.createSequentialGroup()
                        .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_bl_mabl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(145, 145, 145)
                        .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(txt_bl_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_nhaptimluongLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(btn_bl_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_nhaptimluongLayout.createSequentialGroup()
                        .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jMonth_bl_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(123, 123, 123)
                        .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jYear_bl_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel_nhaptimluongLayout.createSequentialGroup()
                .addComponent(jLabel81)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_nhaptimluongLayout.setVerticalGroup(
            jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_nhaptimluongLayout.createSequentialGroup()
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel21))
                .addGap(4, 4, 4)
                .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jYear_bl_nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonth_bl_thang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_bl_matx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_bl_mabl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel_nhaptimluongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_bl_tinhluong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bl_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bl_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl_bl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lương", "Mã tx", "Đơn ht", "Đơn ch", "Đơn hh", "Đơn xn", "Thưởng", "Phạt", "Tổng lương", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(tbl_bl);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane14)
                    .addComponent(jPanel_nhaptimluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_nhaptimluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab_admin1.addTab("Quản lí lương shipper", jPanel31);

        jLabel93.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel93.setText("Thống kê-báo cáo");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel93)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbl_tkbc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Time", "Tổng đơn ht", "Tổng đơn ch", "Tổng đơn hh", "Tổng đơn dg", "Tổng doanh thu", "Qũy lương shipper", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(tbl_tkbc);
        if (tbl_tkbc.getColumnModel().getColumnCount() > 0) {
            tbl_tkbc.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbl_tkbc.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbl_tkbc.getColumnModel().getColumn(2).setPreferredWidth(2);
            tbl_tkbc.getColumnModel().getColumn(3).setPreferredWidth(20);
            tbl_tkbc.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton1.setText("Thống kê tháng hiện tại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tkbc_rs.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tkbc_rs.setText("Reset");
        tkbc_rs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkbc_rsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYear_tkbc, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(172, 172, 172)
                .addComponent(tkbc_rs, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tkbc_rs, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jYear_tkbc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_baocaoLayout = new javax.swing.GroupLayout(jPanel_baocao);
        jPanel_baocao.setLayout(jPanel_baocaoLayout);
        jPanel_baocaoLayout.setHorizontalGroup(
            jPanel_baocaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_baocaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_baocaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_baocaoLayout.setVerticalGroup(
            jPanel_baocaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_baocaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_admin1.addTab("Thống kê, báo cáo", jPanel_baocao);

        javax.swing.GroupLayout jPanel_chuatabLayout = new javax.swing.GroupLayout(jPanel_chuatab);
        jPanel_chuatab.setLayout(jPanel_chuatabLayout);
        jPanel_chuatabLayout.setHorizontalGroup(
            jPanel_chuatabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_chuatabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab_admin1)
                .addContainerGap())
        );
        jPanel_chuatabLayout.setVerticalGroup(
            jPanel_chuatabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_chuatabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab_admin1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton5.setText("Cá nhân");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_QLIPhanMemLayout = new javax.swing.GroupLayout(jPanel_QLIPhanMem);
        jPanel_QLIPhanMem.setLayout(jPanel_QLIPhanMemLayout);
        jPanel_QLIPhanMemLayout.setHorizontalGroup(
            jPanel_QLIPhanMemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_QLIPhanMemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_QLIPhanMemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_chuatab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_QLIPhanMemLayout.createSequentialGroup()
                        .addComponent(jLb_tenpm, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_QLIPhanMemLayout.setVerticalGroup(
            jPanel_QLIPhanMemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_QLIPhanMemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_QLIPhanMemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLb_tenpm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_chuatab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel_QLIPhanMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel_QLIPhanMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tsp_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tsp_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tsp_userActionPerformed

    private void txt_tsp_diachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tsp_diachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tsp_diachiActionPerformed

    private void txt_tsp_matxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tsp_matxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tsp_matxActionPerformed

    private void txt_tsp_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tsp_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tsp_tenActionPerformed

    private void txt_tsp_bienxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tsp_bienxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tsp_bienxeActionPerformed

    private void txt_tsp_tuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tsp_tuoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tsp_tuoiActionPerformed

    private void bt_tsp_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tsp_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_tsp_nuActionPerformed

    private void btn_tsp_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tsp_themActionPerformed
        // TODO add your handling code here:
        String matx = txt_tsp_matx.getText();
        String ten = txt_tsp_ten.getText();
        int tuoi = Integer.parseInt(txt_tsp_tuoi.getText());
        String bienxe = txt_tsp_bienxe.getText();
        String cccd = txt_tsp_cccd.getText();
        String sdt = txt_tsp_sdt.getText();
        String diachi = txt_tsp_diachi.getText();
        double luong = Double.parseDouble(txt_tsp_luong.getText());
        float sao = 5;
        String user = txt_tsp_user.getText();
        String gt = "";
        if (bt_tsp_nam.isSelected()) {
            gt = "Nam";
        } else if (bt_tsp_nu.isSelected()) {
            gt = "Nữ";
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính");
        }
        //System.err.println(GT);
        //tao doi tuong tkdn moi
        TKDangNhap tk = new TKDangNhap(user, "1", 1);
        //them vao csdl
        CT_TKDangNhap.insert(tk);
        Shiper sp = new Shiper(matx, luong, sao, bienxe, ten, gt, tuoi, cccd, sdt, diachi, user);
        CT_Shiper.insert(sp);
        JOptionPane.showMessageDialog(null, "Thêm thành công Shipper");
        showShiper();
    }//GEN-LAST:event_btn_tsp_themActionPerformed

    private void btn_tsp_nhaplaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tsp_nhaplaiActionPerformed
        // TODO add your handling code here:
        txt_tsp_cccd.setText("");
        txt_tsp_matx.setText("");
        txt_tsp_ten.setText("");
        txt_tsp_luong.setText("");
        txt_tsp_diachi.setText("");
        txt_tsp_sdt.setText("");
        txt_tsp_tuoi.setText("");
        txt_tsp_user.setText("");
    }//GEN-LAST:event_btn_tsp_nhaplaiActionPerformed

    private void btn_tsp_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tsp_backActionPerformed
        // TODO add your handling code here:       
        jDialog_tsp.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_btn_tsp_backActionPerformed

    private void txt_tdh_tenspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tdh_tenspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tdh_tenspActionPerformed

    private void txt_tdh_giatrihhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tdh_giatrihhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tdh_giatrihhActionPerformed

    private void btn_tdh_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tdh_themActionPerformed
        // TODO add your handling code here:
        String matx = "";
        String makh = "";
        int maloaihh = 0;
        boolean tdh = true;
        int index = cbb_tdh_masp.getSelectedIndex();
        if (index > 0) {
            matx = cbb_tdh_masp.getItemAt(index);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã shipper");
            tdh = false;
        }
        int index1 = cbb_tdh_makh.getSelectedIndex();
        if (index1 > 0) {
            makh = cbb_tdh_makh.getItemAt(index1);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã khách hàng");
            tdh = false;
        }
        int index2 = cbb_tdh_maloaihh.getSelectedIndex();
        if (index2 > 0) {
            maloaihh = Integer.parseInt(cbb_tdh_maloaihh.getItemAt(index2));
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã loại hàng hóa");
            tdh = false;
        }
        String diachi = txt_tdh_tenkh.getText();
        String mattdh = "dg";
        //jdatechoser trả về util.date
        java.util.Date date = jDate_tdh_time.getDate();
        //gọi hàm doi util.date sang sql.date
        java.sql.Date tggiao = convertUtilDateToSqlDate(date);
        double giatrihh = Double.parseDouble(txt_tdh_giatrihh.getText());
        float sao = 5;
        //String matx, int maloaihh, String makh, String diachigiao, String mattdh, Date tggiao, double giatrihh, float sao
        if (tdh = true) {
            DonHang dh = new DonHang(matx, maloaihh, makh, diachi, mattdh, tggiao, giatrihh, sao);
            CT_DonHang.insert(dh);
            JOptionPane.showMessageDialog(null, "Thêm thành công đơn hàng");
            showDonhang();
        }
    }//GEN-LAST:event_btn_tdh_themActionPerformed

    private void btn_tdh_nhaplaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tdh_nhaplaiActionPerformed
        // TODO add your handling code here:
        //txt_tdh_matx.setText("");
        cbb_tdh_makh.setSelectedIndex(0);
        cbb_tdh_maloaihh.setSelectedIndex(0);
        cbb_tdh_masp.setSelectedIndex(0);
        txt_tdh_tensp.setText("");
        //txt_tdh_makh.setText("");
        txt_tdh_tenkh.setText("");
        jDate_tdh_time.setDate(null);
        txt_tdh_giatrihh.setText("");
    }//GEN-LAST:event_btn_tdh_nhaplaiActionPerformed

    private void btn_tdh_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tdh_backActionPerformed
        // TODO add your handling code here:
        jDialog_tdh.setVisible(false);
        this.setVisible(true);

    }//GEN-LAST:event_btn_tdh_backActionPerformed

    private void txt_sp_matxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sp_matxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sp_matxActionPerformed

    private void txt_sp_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sp_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sp_tenActionPerformed

    private void btn_sp_nhaplaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sp_nhaplaiActionPerformed
        // TODO add your handling code here:
        txt_sp_matx.setText("");
        txt_sp_ten.setText("");
        txt_sp_bienxe.setText("");
        txt_sp_sdt.setText("");
    }//GEN-LAST:event_btn_sp_nhaplaiActionPerformed

    private void btn_sp_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sp_themActionPerformed
        // TODO add your handling code here:
        jDialog_tsp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_sp_themActionPerformed

    private void btn_sp_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sp_timkiemActionPerformed
        // TODO add your handling code here:
        String matx = txt_sp_matx.getText();
        String tentx = txt_sp_ten.getText();
        String bienxe = txt_sp_bienxe.getText();
        String sdt = txt_sp_sdt.getText();
        shiperList = CT_Shiper.finddk(matx, tentx, bienxe, sdt);
        table_sp.setRowCount(0);
        for (Shiper sp : shiperList) {
            table_sp.addRow(new Object[]{sp.getMatx(), sp.getTen(), sp.getGt(),
                sp.getTuoi(), sp.getBienxe(), sp.getCccd(), sp.getSdt(), sp.getDiachi(),
                sp.getLuongcb(), sp.getSao(), sp.getUser()});
    }//GEN-LAST:event_btn_sp_timkiemActionPerformed
    }
    private void txt_sp_bienxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sp_bienxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sp_bienxeActionPerformed

    private void btn_sp_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sp_resetActionPerformed
        // TODO add your handling code here:
        showShiper();
    }//GEN-LAST:event_btn_sp_resetActionPerformed

    private void txt_dh_madhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dh_madhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dh_madhActionPerformed

    private void btn_dh_nhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dh_nhaphangActionPerformed
        // TODO add your handling code here:
        txt_tdh_tensp.setText("");
        //txt_tdh_makh.setText("");
        txt_tdh_tenkh.setText("");
        jDate_tdh_time.setDate(null);
        txt_tdh_giatrihh.setText("");
    }//GEN-LAST:event_btn_dh_nhaphangActionPerformed

    private void btn_dh_themdhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dh_themdhActionPerformed
        // TODO add your handling code here:
        jDialog_tdh.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_dh_themdhActionPerformed

    private void btn_dh_timdhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dh_timdhActionPerformed
        // TODO add your handling code here:
        String madh = txt_dh_madh.getText();
        String matx = txt_dh_matx.getText();
        String mattdh = txt_dh_mattdh.getText();
        String diachi = txt_dh_diachi.getText();
        if (jDate_dh_time.getDate() == null || jDateChooser1.getDate() == null) {
            donhangList = CT_DonHang.findknotime(madh, matx, mattdh, diachi);
        } else {
            java.util.Date timetu = jDate_dh_time.getDate();
            //gọi hàm doi util.date sang sql.date
            java.sql.Date tggiaotu = convertUtilDateToSqlDate(timetu);
            java.util.Date timeden = jDateChooser1.getDate();
            java.sql.Date tggiaoden = convertUtilDateToSqlDate(timeden);
            donhangList = CT_DonHang.findk(tggiaotu, tggiaoden, madh, matx, mattdh, diachi);
        }
        table_dh.setRowCount(0);
        for (DonHang dh : donhangList) {
            table_dh.addRow(new Object[]{dh.getMadh(), dh.getMatx(), dh.getMaloaihh(),
                dh.getMakh(), dh.getDiachigiao(), dh.getMattdh(), dh.getTggiao(),
                dh.getGiatrihh(), dh.getSao()});
        }
    }//GEN-LAST:event_btn_dh_timdhActionPerformed

    private void btn_dh_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dh_resetActionPerformed
        // TODO add your handling code here:
        showDonhang();
    }//GEN-LAST:event_btn_dh_resetActionPerformed

    private void txt_kh_makhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kh_makhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kh_makhActionPerformed

    private void btn_kh_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kh_resetActionPerformed
        // TODO add your handling code here:
        showKhachhang();
    }//GEN-LAST:event_btn_kh_resetActionPerformed

    private void btn_kh_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kh_themActionPerformed
        // TODO add your handling code here:
        jDialog_tkh.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_kh_themActionPerformed

    private void btn_kh_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kh_timkiemActionPerformed
        // TODO add your handling code here:   
        String makh = txt_kh_makh.getText();
        String ten = txt_kh_tenkh.getText();
        String sdt = txt_kh_sdt.getText();
        String diachi = txt_kh_diachi.getText();
        khachhangList = CT_KhachHang.finddk(makh, ten, sdt, diachi);
        table_kh.setRowCount(0);
        for (KhachHang kh : khachhangList) {
            table_kh.addRow(new Object[]{kh.getMakh(), kh.getTen(), kh.getGt(),
                kh.getTuoi(), kh.getCccd(), kh.getSdt(), kh.getDiachi(),
                kh.getUser()});
        }

    }//GEN-LAST:event_btn_kh_timkiemActionPerformed

    private void btn_kh_nhaplaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kh_nhaplaiActionPerformed
        // TODO add your handling code here:
        txt_kh_makh.setText("");
        txt_kh_tenkh.setText("");
        txt_kh_sdt.setText("");
        txt_kh_diachi.setText("");
    }//GEN-LAST:event_btn_kh_nhaplaiActionPerformed

    private void btn_bl_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bl_resetActionPerformed
        // TODO add your handling code here:
        showBangluong();
    }//GEN-LAST:event_btn_bl_resetActionPerformed

    private void btn_bl_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bl_timActionPerformed
        // TODO add your handling code here:
        String mabl = txt_bl_mabl.getText();
        String matx = txt_bl_matx.getText();
        int thang = jMonth_bl_thang.getMonth();
        int nam = jYear_bl_nam.getYear();
        bangluongList = CT_BangLuong.findk(thang, nam, mabl, matx);

        table_bl.setRowCount(0);
        for (bangluong bl : bangluongList) {
            table_bl.addRow(new Object[]{bl.getMabl(), bl.getMatx(), bl.getDonht(), bl.getDonch(),
                bl.getDonhong(), bl.getDonxinnghi(), bl.getThuong(), bl.getPhat(), bl.getTongluong(), bl.getTime()});
        }

    }//GEN-LAST:event_btn_bl_timActionPerformed

    private void btn_bl_tinhluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bl_tinhluongActionPerformed
        // TODO add your handling code here:
        shiperList = CT_Shiper.findAll();
        int i=1;
        table_tkbc.setRowCount(0);
        for(Shiper sp : shiperList){
            long mi = System.currentTimeMillis();
            java.sql.Date time = new java.sql.Date(mi);
            String matx= sp.getMatx();
            int donht=CT_DonHang.tinhdonhang(matx, "ht", 8, 2021);
            int donch=CT_DonHang.tinhdonhang(matx, "ht", 8, 2021);
            int donhh=CT_DonHang.tinhdonhang(matx, "ht", 8, 2021);
            int donxn=CT_Donxinnghi.tinhdonnghi(matx, 8, 2021);
            double thuong=CT_TinhLuong.tinhthuong(matx, 8, 2021);
            double phat=CT_TinhLuong.tinhphat(matx, 8, 2021);
            double tongluong=CT_TinhLuong.tinhluong(matx, 8, 2021); 
            bangluong bl=new bangluong(matx, time, donht, donch, donhh, donxn, thuong, phat, tongluong);
            CT_BangLuong.insert(bl);
            table_bl.addRow(new Object[]{i, bl.getMatx(), bl.getDonht(), bl.getDonch(),
                bl.getDonhong(), bl.getDonxinnghi(), bl.getThuong(), bl.getPhat(), bl.getTongluong(), bl.getTime()});
            i++;    
        }
    }//GEN-LAST:event_btn_bl_tinhluongActionPerformed

    private void txt_bl_matxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bl_matxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bl_matxActionPerformed

    private void txt_tkh_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tkh_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tkh_userActionPerformed

    private void txt_tkh_diachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tkh_diachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tkh_diachiActionPerformed

    private void txt_tkh_matxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tkh_matxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tkh_matxActionPerformed

    private void txt_tkh_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tkh_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tkh_tenActionPerformed

    private void txt_tkh_tuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tkh_tuoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tkh_tuoiActionPerformed

    private void bt_tkh_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tkh_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_tkh_nuActionPerformed

    private void btn_tkh_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tkh_themActionPerformed
        // TODO add your handling code here:
        String makh = txt_tkh_matx.getText();
        String ten = txt_tkh_ten.getText();
        int tuoi = Integer.parseInt(txt_tkh_tuoi.getText());
        String cccd = txt_tkh_cccd.getText();
        String sdt = txt_tkh_sdt.getText();
        String diachi = txt_tkh_diachi.getText();
        String user = txt_tkh_user.getText();
        String gt = "";
        if (bt_tkh_nam.isSelected()) {
            gt = "Nam";
        } else if (bt_tkh_nu.isSelected()) {
            gt = "Nữ";
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính");
        }
        //System.err.println(GT);
        //tao doi tuong tkdn moi
        TKDangNhap tk = new TKDangNhap(user, "1", 0);
        //them vao csdl
        CT_TKDangNhap.insert(tk);
        KhachHang kh = new KhachHang(makh, ten, gt, tuoi, cccd, sdt, diachi, user);

        CT_KhachHang.insert(kh);
        JOptionPane.showMessageDialog(null, "Thêm thành công khách hàng");
        showKhachhang();


    }//GEN-LAST:event_btn_tkh_themActionPerformed

    private void btn_tsp_nhaplai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tsp_nhaplai1ActionPerformed
        // TODO add your handling code here:
        txt_tkh_cccd.setText("");
        txt_tkh_diachi.setText("");
        txt_tkh_matx.setText("");
        txt_tkh_sdt.setText("");
        txt_tkh_ten.setText("");
        txt_tkh_tuoi.setText("");
        txt_tkh_user.setText("");
        bt_tkh_nam.setSelected(true);
    }//GEN-LAST:event_btn_tsp_nhaplai1ActionPerformed

    private void btn_tsp_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tsp_back1ActionPerformed
        // TODO add your handling code here:
        jDialog_tkh.setVisible(false);
        this.setVisible(true);

    }//GEN-LAST:event_btn_tsp_back1ActionPerformed

    private void menuitem_sp_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_sp_xoaActionPerformed
        // TODO add your handling code here:
        int row = tbl_sp.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            Shiper sp = shiperList.get(row); //lay ra sinh vien thu row 
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa shipper này không?");
            if (option == 0) {
                CT_Shiper.delete(sp.getMatx());
                showShiper();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xin chọn Shipper trước");
        }
    }//GEN-LAST:event_menuitem_sp_xoaActionPerformed

    private void menuitem_sp_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_sp_suaActionPerformed
        // TODO add your handling code here:
        int row = tbl_sp.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            Shiper sp = shiperList.get(row); //lay ra sinh vien thu row 
            String matx = sp.getMatx();
            setsuasp(matx);
            jDialog_suasp.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Xin chọn Shipper trước");
        }

    }//GEN-LAST:event_menuitem_sp_suaActionPerformed

    private void btn_jDia_suaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jDia_suaspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_jDia_suaspActionPerformed

    private void bt_suasp_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suasp_namActionPerformed
        // TODO add your handling code here:
        //***********;
        String matx = txt_ssp_ma.getText();
        String ten = txt_ssp_ten.getText();
        int tuoi = Integer.parseInt(txt_ssp_tuoi.getText());
        String bienxe = txt_ssp_bienxe.getText();
        String cccd = txt_ssp_cccd.getText();
        String sdt = txt_ssp_sdt.getText();
        String diachi = txt_ssp_dc.getText();
        double luongcb = Double.parseDouble(txt_ssp_luong.getText());
        float sao = Float.parseFloat(txt_ssp_sao.getText());
        String user = txt_ssp_user.getText();
        String gt = "";
        if (bt_suasp_Nam.isSelected()) {
            gt = "Nam";
        } else {
            gt = "Nữ";
        }
        Shiper sp = new Shiper(matx, luongcb, sao, bienxe, ten, gt, tuoi, cccd, sdt, diachi, user);
        int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin shipper?");
        if (option == 0) {
            CT_Shiper.update(sp);
            JOptionPane.showMessageDialog(null, "Sửa thành công shipper");
            showShiper();
        }
        //System.err.println(GT);
        //tao doi tuong tkdn moi
        //TKDangNhap tk = new TKDangNhap(user, "1", 0);
        //them vao csdl

    }//GEN-LAST:event_bt_suasp_namActionPerformed

    private void txt_ssp_luongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ssp_luongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ssp_luongActionPerformed

    private void menuitem_dh_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_dh_suaActionPerformed
        // TODO add your handling code here:
        int row = tbl_dh.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            DonHang dh = donhangList.get(row); //lay ra sinh vien thu row 
            int madh = dh.getMadh();
            setsuadh(madh);
            jDialog_suadh.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Xin chọn đơn hàng trước");
        }

    }//GEN-LAST:event_menuitem_dh_suaActionPerformed

    private void menuitem_dh_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_dh_xoaActionPerformed
        // TODO add your handling code here:
        int row = tbl_dh.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            DonHang dh = donhangList.get(row); //lay ra sinh vien thu row 
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa đơn hàng này không?");
            if (option == 0) {
                CT_DonHang.delete(dh.getMadh());
                showDonhang();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xin chọn đơn hàng trước!");
        }
    }//GEN-LAST:event_menuitem_dh_xoaActionPerformed

    private void bt_suadh_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suadh_suaActionPerformed
        // TODO add your handling code here:
        int madh = Integer.parseInt(txt_suadh_madh.getText());
        int maloaihh = Integer.parseInt(txt_suadh_maloaihh.getText());
        String mattdh = txt_suadh_mattdh.getText();
        java.util.Date date = jDate_suadh_time.getDate();
        java.sql.Date time = convertUtilDateToSqlDate(date);
        String diachi = txt_suadh_dc.getText();
        double giatrihh = Double.parseDouble(txt_suadh_giatrihh.getText());
        float sao = Float.parseFloat(txt_suadh_sao.getText());
        String matx = txt_suadh_matx.getText();
        String makh = txt_suadh_makh.getText();
        DonHang dh = new DonHang(madh, matx, maloaihh, makh, diachi, mattdh, time, giatrihh, sao);
        int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin đơn hàng?");
        if (option == 0) {
            CT_DonHang.update(dh);
            CT_Shiper.updatesao(matx, CT_Shiper.tinhsao(matx));
            JOptionPane.showMessageDialog(null, "Sửa thành công shipper");
            showDonhang();
        }

    }//GEN-LAST:event_bt_suadh_suaActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        jDialog_suadh.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void txt_suadh_tenttdhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_suadh_tenttdhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_suadh_tenttdhActionPerformed

    private void btn_suakh_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suakh_backActionPerformed
        // TODO add your handling code here:
        jDialog_suakh.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_btn_suakh_backActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_suahh_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suahh_backActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_suahh_backActionPerformed

    private void btn_suahh_lưuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suahh_lưuActionPerformed
        // TODO add your handling code here:
        int maloaihh=Integer.parseInt(txt_suahh_ma.getText());
        String tenhh=txt_suahh_ten.getText();
        double phiship = Double.parseDouble(txt_suahh_ps.getText());
        HangHoa hh = new HangHoa(maloaihh, tenhh, phiship);
        CT_HangHoa.update(hh);
        showHanghoa();
    }//GEN-LAST:event_btn_suahh_lưuActionPerformed

    private void txt_suadh_maloaihhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_suadh_maloaihhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_suadh_maloaihhActionPerformed

    private void txt_suadh_sodtkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_suadh_sodtkhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_suadh_sodtkhActionPerformed

    private void txt_tdh_phishipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tdh_phishipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tdh_phishipActionPerformed

    private void cbb_tdh_maspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tdh_maspActionPerformed
        // TODO add your handling code here:
        int index = cbb_tdh_masp.getSelectedIndex();
        if (index >= 0) {
            String matx = cbb_tdh_masp.getItemAt(index);
            txt_tdh_tensp.setText(CT_Shiper.findMatx(matx).getTen());
        }
    }//GEN-LAST:event_cbb_tdh_maspActionPerformed

    private void cbb_tdh_maloaihhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tdh_maloaihhActionPerformed
        // TODO add your handling code here:
        int index = cbb_tdh_maloaihh.getSelectedIndex();
        if (index >= 0) {
            int maloaihh = Integer.parseInt(cbb_tdh_maloaihh.getItemAt(index));
            txt_tdh_tenmaloaihh1.setText(CT_HangHoa.findMaloaihh(maloaihh).getTenhh());
            txt_tdh_phiship.setText(String.valueOf(CT_HangHoa.findMaloaihh(maloaihh).getPhiship()));
        }
    }//GEN-LAST:event_cbb_tdh_maloaihhActionPerformed

    private void txt_tdh_tenmaloaihh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tdh_tenmaloaihh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tdh_tenmaloaihh1ActionPerformed

    private void cbb_tdh_makhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tdh_makhActionPerformed
        // TODO add your handling code here:
        int index = cbb_tdh_makh.getSelectedIndex();
        if (index >= 0) {
            String makh = cbb_tdh_makh.getItemAt(index);
            txt_tdh_tenkh.setText(CT_KhachHang.findmakh(makh).getTen());
        }
    }//GEN-LAST:event_cbb_tdh_makhActionPerformed

    private void menuitem_suakh_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_suakh_suaActionPerformed
        // TODO add your handling code here:
        int row = tbl_kh.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            KhachHang kh = khachhangList.get(row); //lay ra sinh vien thu row 
            String makh = kh.getMakh();
            setsuakh(makh);
            jDialog_suakh.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Xin chọn khách hàng trước");
        }

    }//GEN-LAST:event_menuitem_suakh_suaActionPerformed

    private void menuitem_suakh_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_suakh_xoaActionPerformed
        // TODO add your handling code here:
        int row = tbl_kh.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            KhachHang kh = khachhangList.get(row); //lay ra sinh vien thu row 
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng này không?");
            if (option == 0) {
                CT_KhachHang.delete(kh.getMakh());
                showKhachhang();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xin chọn khách hàng trước");
        }
    }//GEN-LAST:event_menuitem_suakh_xoaActionPerformed

    private void btn_suakh_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suakh_luuActionPerformed
        // TODO add your handling code here:
        String makh = txt_suakh_makh.getText();
        String tenkh = txt_suakh_tenkh.getText();
        int tuoi = Integer.parseInt(txt_suakh_tuoi.getText());
        String cccd = txt_suakh_cccd.getText();
        String diachi = txt_suakh_dc.getText();
        String sdt = txt_suakh_sdt.getText();
        String user = txt_suakh_user.getText();
        String gt = "";
        if (bt_suakh_nam.isSelected()) {
            gt = "Nam";
        } else {
            gt = "Nữ";
        }
        KhachHang kh = new KhachHang(makh, tenkh, gt, tuoi, cccd, sdt, diachi, user);
        int row = tbl_kh.getSelectedRow(); // lay dong dang duoc chon
        if (row >= 0) {
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa khách hàng này không?");
            if (option == 0) {
                CT_KhachHang.update(kh);
                JOptionPane.showMessageDialog(this, "Sửa thông tin thành công");
                showKhachhang();

            }
        }

    }//GEN-LAST:event_btn_suakh_luuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //thongkebaocao tkbc=new thongkebaocao;
        long mi = System.currentTimeMillis();
        java.sql.Date time = new java.sql.Date(mi);
        int tdht = CT_DonHang.tinhdonhang("", "ht", 8, 2021);
        int tdch = CT_DonHang.tinhdonhang("", "ch", 8, 2021);
        int tdhh = CT_DonHang.tinhdonhang("", "hh", 8, 2021);
        int tddg = CT_DonHang.tinhdonhang("", "dg", 8, 2021);
        double quyluongsp = CT_thongkebaocao.quyluongsp(8, 2021);
        double tongdoanhthu = CT_thongkebaocao.tongdoanhthu(8, 2021);
        double doanhthu = tongdoanhthu - quyluongsp;
        thongkebaocao tkbc = new thongkebaocao(time, tdht, tdch, tdhh, tddg, tongdoanhthu, quyluongsp, doanhthu, doanhthu);
        CT_thongkebaocao.insert(tkbc);
        table_tkbc.setRowCount(0);
        table_tkbc.addRow(new Object[]{tkbc.getTime(), tkbc.getTdht(),
            tkbc.getTdch(), tkbc.getTdhh(), tkbc.getTddg(), tkbc.getTongdoanhthu(), tkbc.getQuyluongsp(),
            tkbc.getDoanhthu()});
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tkbc_rsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkbc_rsActionPerformed
        // TODO add your handling code here:
        showBaocao(2021);
    }//GEN-LAST:event_tkbc_rsActionPerformed

    private void menuitem_suatpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_suatpActionPerformed
        // TODO add your handling code here:
        jDialog_sthuongphat.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuitem_suatpActionPerformed

    private void menuitem_suaphishipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitem_suaphishipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuitem_suaphishipActionPerformed

    private void btn_suakh_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suakh_back1ActionPerformed
        // TODO add your handling code here:
        jDialog_suaadmin.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_btn_suakh_back1ActionPerformed

    private void btn_suakh_luu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suakh_luu1ActionPerformed
        // TODO add your handling code here:
        //Admin ad = CT_Admin.finduser(user);
        String maad=txt_suaad_ma.getText();
        String tenad=txt_suaad_ten.getText();
        int tuoi=Integer.parseInt(txt_suaad_tuoi1.getText());
        String cccd=txt_suaad_cccd1.getText();
        String diachi=txt_suaad_dc1.getText();
        String sdt = txt_suaad_sdt1.getText();
        String user=txt_suaad_user1.getText();
        String gt = "";
        if(bt_suaad_nam1.isSelected()){
            gt="Nam";
        } else {
            gt="Nữ";
        }
        Admin ad = new Admin(maad, tenad, gt, tuoi, cccd, sdt, diachi, user);
        int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin bản thân?");
            if (option == 0) {
                CT_Admin.update(ad);
                JOptionPane.showMessageDialog(this, "Sửa thông tin thành công");
            
            }       
    }//GEN-LAST:event_btn_suakh_luu1ActionPerformed

    private void btn_dmkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dmkActionPerformed
        // TODO add your handling code here:
        jDialog_doimk.setVisible(true);
        jDialog_suaadmin.setVisible(false);
        this.setVisible(false);
    }//GEN-LAST:event_btn_dmkActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jDialog_suaadmin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_doimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doimkActionPerformed
        // TODO add your handling code here:
        String mkcu= new String (jPassword_mkcu.getPassword());
        String mkmoi=new String(jPasswordF_mkmoi.getPassword());
        String mkmoi2=new String(jPassword_mkmoi2.getPassword());
        
        
    }//GEN-LAST:event_btn_doimkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
    
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bt_gr_suasp;
    private javax.swing.JRadioButton bt_suaad_nam1;
    private javax.swing.JRadioButton bt_suaad_nu1;
    private javax.swing.JButton bt_suadh_sua;
    private javax.swing.JRadioButton bt_suakh_nam;
    private javax.swing.JRadioButton bt_suakh_nu;
    private javax.swing.JRadioButton bt_suasp_Nam;
    private javax.swing.JButton bt_suasp_nam;
    private javax.swing.JRadioButton bt_suasp_nu;
    private javax.swing.JRadioButton bt_tkh_nam;
    private javax.swing.JRadioButton bt_tkh_nu;
    private javax.swing.JRadioButton bt_tsp_nam;
    private javax.swing.JRadioButton bt_tsp_nu;
    private javax.swing.ButtonGroup btgr_suakh;
    private javax.swing.ButtonGroup btgr_tkh;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_bl_reset;
    private javax.swing.JButton btn_bl_tim;
    private javax.swing.JButton btn_bl_tinhluong;
    private javax.swing.JButton btn_dh_nhaphang;
    private javax.swing.JButton btn_dh_reset;
    private javax.swing.JButton btn_dh_themdh;
    private javax.swing.JButton btn_dh_timdh;
    private javax.swing.JButton btn_dmk;
    private javax.swing.JButton btn_doimk;
    private javax.swing.JButton btn_jDia_suasp;
    private javax.swing.JButton btn_kh_nhaplai;
    private javax.swing.JButton btn_kh_reset;
    private javax.swing.JButton btn_kh_them;
    private javax.swing.JButton btn_kh_timkiem;
    private javax.swing.JButton btn_sp_nhaplai;
    private javax.swing.JButton btn_sp_reset;
    private javax.swing.JButton btn_sp_them;
    private javax.swing.JButton btn_sp_timkiem;
    private javax.swing.JButton btn_suahh_back;
    private javax.swing.JButton btn_suahh_lưu;
    private javax.swing.JButton btn_suakh_back;
    private javax.swing.JButton btn_suakh_back1;
    private javax.swing.JButton btn_suakh_luu;
    private javax.swing.JButton btn_suakh_luu1;
    private javax.swing.JButton btn_tdh_back;
    private javax.swing.JButton btn_tdh_nhaplai;
    private javax.swing.JButton btn_tdh_them;
    private javax.swing.JButton btn_tkh_them;
    private javax.swing.JButton btn_tsp_back;
    private javax.swing.JButton btn_tsp_back1;
    private javax.swing.JButton btn_tsp_nhaplai;
    private javax.swing.JButton btn_tsp_nhaplai1;
    private javax.swing.JButton btn_tsp_them;
    private javax.swing.ButtonGroup buttonGr_baocao;
    private javax.swing.ButtonGroup button_tsp;
    private javax.swing.JComboBox<String> cbb_tdh_makh;
    private javax.swing.JComboBox<String> cbb_tdh_maloaihh;
    private javax.swing.JComboBox<String> cbb_tdh_masp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDate_dh_time;
    private com.toedter.calendar.JDateChooser jDate_suadh_time;
    private com.toedter.calendar.JDateChooser jDate_tdh_time;
    private javax.swing.JDialog jDialog_doimk;
    private javax.swing.JDialog jDialog_sphiship;
    private javax.swing.JDialog jDialog_sthuongphat;
    private javax.swing.JDialog jDialog_suaadmin;
    private javax.swing.JDialog jDialog_suadh;
    private javax.swing.JDialog jDialog_suakh;
    private javax.swing.JDialog jDialog_suasp;
    private javax.swing.JDialog jDialog_tdh;
    private javax.swing.JDialog jDialog_tkh;
    private javax.swing.JDialog jDialog_tsp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLb_tenpm;
    private com.toedter.calendar.JMonthChooser jMonth_bl_thang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_QLIPhanMem;
    private javax.swing.JPanel jPanel_baocao;
    private javax.swing.JPanel jPanel_chuatab;
    private javax.swing.JPanel jPanel_gdthemdh;
    private javax.swing.JPanel jPanel_hanghoa;
    private javax.swing.JPanel jPanel_nhap;
    private javax.swing.JPanel jPanel_nhaptimluong;
    private javax.swing.JPanel jPanel_qlidh;
    private javax.swing.JPanel jPanel_qlikh;
    private javax.swing.JPanel jPanel_qlisp1;
    private javax.swing.JPanel jPanel_thuongphat;
    private javax.swing.JPasswordField jPasswordF_mkmoi;
    private javax.swing.JPasswordField jPassword_mkcu;
    private javax.swing.JPasswordField jPassword_mkmoi2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private com.toedter.calendar.JYearChooser jYear_bl_nam;
    private com.toedter.calendar.JYearChooser jYear_tkbc;
    private javax.swing.JMenuItem menuitem_dh_sua;
    private javax.swing.JMenuItem menuitem_dh_xoa;
    private javax.swing.JMenuItem menuitem_sp_sua;
    private javax.swing.JMenuItem menuitem_sp_xoa;
    private javax.swing.JMenuItem menuitem_suakh_sua;
    private javax.swing.JMenuItem menuitem_suakh_xoa;
    private javax.swing.JMenuItem menuitem_suaphiship;
    private javax.swing.JMenuItem menuitem_suatp;
    private javax.swing.JPopupMenu popupmenu_sp;
    private javax.swing.JPopupMenu popupmenu_suadh;
    private javax.swing.JPopupMenu popupmenu_suakh;
    private javax.swing.JPopupMenu popupmenu_suaphiship;
    private javax.swing.JPopupMenu popupmenu_suathuongphat;
    private javax.swing.JLabel sfd;
    private javax.swing.JTabbedPane tab_admin1;
    private javax.swing.JTable tbl_bl;
    private javax.swing.JTable tbl_dh;
    private javax.swing.JTable tbl_hh;
    private javax.swing.JTable tbl_kh;
    private javax.swing.JTable tbl_sp;
    private javax.swing.JTable tbl_thuongphat;
    private javax.swing.JTable tbl_tkbc;
    private javax.swing.JButton tkbc_rs;
    private javax.swing.JTextField txt_bl_mabl;
    private javax.swing.JTextField txt_bl_matx;
    private javax.swing.JTextField txt_dh_diachi;
    private javax.swing.JTextField txt_dh_madh;
    private javax.swing.JTextField txt_dh_mattdh;
    private javax.swing.JTextField txt_dh_matx;
    private javax.swing.JTextField txt_kh_diachi;
    private javax.swing.JTextField txt_kh_makh;
    private javax.swing.JTextField txt_kh_sdt;
    private javax.swing.JTextField txt_kh_tenkh;
    private javax.swing.JTextField txt_sp_bienxe;
    private javax.swing.JTextField txt_sp_matx;
    private javax.swing.JTextField txt_sp_sdt;
    private javax.swing.JTextField txt_sp_ten;
    private javax.swing.JTextField txt_ssp_bienxe;
    private javax.swing.JTextField txt_ssp_cccd;
    private javax.swing.JTextField txt_ssp_dc;
    private javax.swing.JTextField txt_ssp_luong;
    private javax.swing.JTextField txt_ssp_ma;
    private javax.swing.JTextField txt_ssp_sao;
    private javax.swing.JTextField txt_ssp_sdt;
    private javax.swing.JTextField txt_ssp_ten;
    private javax.swing.JTextField txt_ssp_tuoi;
    private javax.swing.JTextField txt_ssp_user;
    private javax.swing.JTextField txt_suaad_cccd1;
    private javax.swing.JTextField txt_suaad_dc1;
    private javax.swing.JTextField txt_suaad_ma;
    private javax.swing.JTextField txt_suaad_sdt1;
    private javax.swing.JTextField txt_suaad_ten;
    private javax.swing.JTextField txt_suaad_tuoi1;
    private javax.swing.JTextField txt_suaad_user1;
    private javax.swing.JTextField txt_suadh_dc;
    private javax.swing.JTextField txt_suadh_giatrihh;
    private javax.swing.JTextField txt_suadh_madh;
    private javax.swing.JTextField txt_suadh_makh;
    private javax.swing.JTextField txt_suadh_maloaihh;
    private javax.swing.JTextField txt_suadh_mattdh;
    javax.swing.JTextField txt_suadh_matx;
    private javax.swing.JTextField txt_suadh_sao;
    private javax.swing.JTextField txt_suadh_sdtsp;
    private javax.swing.JTextField txt_suadh_sodtkh;
    private javax.swing.JTextField txt_suadh_tenhh;
    private javax.swing.JTextField txt_suadh_tenkh;
    private javax.swing.JTextField txt_suadh_tensp;
    private javax.swing.JTextField txt_suadh_tenttdh;
    private javax.swing.JTextField txt_suahh_ma;
    private javax.swing.JTextField txt_suahh_ps;
    private javax.swing.JTextField txt_suahh_ten;
    private javax.swing.JTextField txt_suakh_cccd;
    private javax.swing.JTextField txt_suakh_dc;
    private javax.swing.JTextField txt_suakh_makh;
    private javax.swing.JTextField txt_suakh_sdt;
    private javax.swing.JTextField txt_suakh_tenkh;
    private javax.swing.JTextField txt_suakh_tuoi;
    private javax.swing.JTextField txt_suakh_user;
    private javax.swing.JTextField txt_tdh_diachi;
    private javax.swing.JTextField txt_tdh_giatrihh;
    private javax.swing.JTextField txt_tdh_phiship;
    private javax.swing.JTextField txt_tdh_tenkh;
    private javax.swing.JTextField txt_tdh_tenmaloaihh1;
    private javax.swing.JTextField txt_tdh_tensp;
    private javax.swing.JTextField txt_tkh_cccd;
    private javax.swing.JTextField txt_tkh_diachi;
    private javax.swing.JTextField txt_tkh_matx;
    private javax.swing.JTextField txt_tkh_sdt;
    private javax.swing.JTextField txt_tkh_ten;
    private javax.swing.JTextField txt_tkh_tuoi;
    private javax.swing.JTextField txt_tkh_user;
    private javax.swing.JTextField txt_tsp_bienxe;
    private javax.swing.JTextField txt_tsp_cccd;
    private javax.swing.JTextField txt_tsp_diachi;
    private javax.swing.JTextField txt_tsp_luong;
    private javax.swing.JTextField txt_tsp_matx;
    private javax.swing.JTextField txt_tsp_sdt;
    private javax.swing.JTextField txt_tsp_ten;
    private javax.swing.JTextField txt_tsp_tuoi;
    private javax.swing.JTextField txt_tsp_user;
    // End of variables declaration//GEN-END:variables

    private Date ConvertDataToDateSql(java.util.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
