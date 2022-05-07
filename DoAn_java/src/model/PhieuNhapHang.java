/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class PhieuNhapHang {
    private int maPhieuNhapHang;   
    private int maNhanVien;
    private int maNhaCungCap;
    private double TongTien;
    private boolean daXoa;
    private Date ngayNhapHang;

    public PhieuNhapHang() {
    }

    public PhieuNhapHang(int maPhieuNhapHang, int maNhanVien, int maNhaCungCap, double TongTien, boolean daXoa, Date ngayNhapHang) {
        this.maPhieuNhapHang = maPhieuNhapHang;
        this.maNhanVien = maNhanVien;
        this.maNhaCungCap = maNhaCungCap;
        this.TongTien = TongTien;
        this.daXoa = daXoa;
        this.ngayNhapHang = ngayNhapHang;
    }

    public int getMaPhieuNhapHang() {
        return maPhieuNhapHang;
    }

    public void setMaPhieuNhapHang(int maPhieuNhapHang) {
        this.maPhieuNhapHang = maPhieuNhapHang;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public Date getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(Date ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }
    
    
}
