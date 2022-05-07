/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class CTPhieuNhapHang {
    private int chiTietPhieuNhapHang;
    private int maLinhKien;
    private int loaiLinhKien;
    private String tenLinhKienString;
    private String xuatSu;
    private double giaBan;
    private String baoHanh;
    private int soLuongNhap;
    private double thanhTien;

    public CTPhieuNhapHang() {
    }

    public CTPhieuNhapHang(int chiTietPhieuNhapHang, int maLinhKien, int loaiLinhKien, String tenLinhKienString, String xuatSu, double giaBan, String baoHanh, int soLuongNhap, double thanhTien) {
        this.chiTietPhieuNhapHang = chiTietPhieuNhapHang;
        this.maLinhKien = maLinhKien;
        this.loaiLinhKien = loaiLinhKien;
        this.tenLinhKienString = tenLinhKienString;
        this.xuatSu = xuatSu;
        this.giaBan = giaBan;
        this.baoHanh = baoHanh;
        this.soLuongNhap = soLuongNhap;
        this.thanhTien = thanhTien;
    }

    public int getChiTietPhieuNhapHang() {
        return chiTietPhieuNhapHang;
    }

    public void setChiTietPhieuNhapHang(int chiTietPhieuNhapHang) {
        this.chiTietPhieuNhapHang = chiTietPhieuNhapHang;
    }

    public int getMaLinhKien() {
        return maLinhKien;
    }

    public void setMaLinhKien(int maLinhKien) {
        this.maLinhKien = maLinhKien;
    }

    public int getLoaiLinhKien() {
        return loaiLinhKien;
    }

    public void setLoaiLinhKien(int loaiLinhKien) {
        this.loaiLinhKien = loaiLinhKien;
    }

    public String getTenLinhKienString() {
        return tenLinhKienString;
    }

    public void setTenLinhKienString(String tenLinhKienString) {
        this.tenLinhKienString = tenLinhKienString;
    }

    public String getXuatSu() {
        return xuatSu;
    }

    public void setXuatSu(String xuatSu) {
        this.xuatSu = xuatSu;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(String baoHanh) {
        this.baoHanh = baoHanh;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
    
}
