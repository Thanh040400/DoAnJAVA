/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private int maNhanVien;
    private int SDT;
    private String HoTen;
    private String DiaChi; 
    private int NamSinh;
    private String GioiTinh;
    private String MaChucVu;
    private String TaiKhoanString;
    private String MatKhau;
    private boolean DaXoa;
    
    
     public NhanVien(int maNhanVien, int SDT, String HoTen, String DiaChi, int NamSinh, String GioiTinh, String MaChucVu, String TaiKhoanString, String MatKhau, boolean DaXoa) {
        this.maNhanVien = maNhanVien;
        this.SDT = SDT;
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.NamSinh = NamSinh;
        this.GioiTinh = GioiTinh;
        this.MaChucVu = MaChucVu;
        this.TaiKhoanString = TaiKhoanString;
        this.MatKhau = MatKhau;
        this.DaXoa = DaXoa;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int NamSinh) {
        this.NamSinh = NamSinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(String MaChucVu) {
        this.MaChucVu = MaChucVu;
    }

    public String getTaiKhoanString() {
        return TaiKhoanString;
    }

    public void setTaiKhoanString(String TaiKhoanString) {
        this.TaiKhoanString = TaiKhoanString;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }
    
}
