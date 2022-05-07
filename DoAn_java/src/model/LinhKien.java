/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class LinhKien {
    private int MaLinhKien;
    private String TenLinhKien;
    private String XuatSu;
    private double GiaBan;
    private String BaoHanh;
    private int SoLuongTon;
    private String HinhAnh;
    private int MaLoaiLinhKien;
    private int MaNCC;
    private boolean DaXoa;
    
    public int getMaLinhKien() {
        return MaLinhKien;
    }

    public void setMaLinhKien(int MaLinhKien) {
        this.MaLinhKien = MaLinhKien;
    }

    public String getTenLinhKien() {
        return TenLinhKien;
    }

    public void setTenLinhKien(String TenLinhKien) {
        this.TenLinhKien = TenLinhKien;
    }

    public String getXuatSu() {
        return XuatSu;
    }

    public void setXuatSu(String XuatSu) {
        this.XuatSu = XuatSu;
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public String getBaoHanh() {
        return BaoHanh;
    }

    public void setBaoHanh(String BaoHanh) {
        this.BaoHanh = BaoHanh;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public int getMaLoaiLinhKien() {
        return MaLoaiLinhKien;
    }

    public void setMaLoaiLinhKien(int MaLoaiLinhKien) {
        this.MaLoaiLinhKien = MaLoaiLinhKien;
    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }
    

    public LinhKien() {
    }

    public LinhKien(int MaLinhKien, String TenLinhKien, String XuatSu, double GiaBan, String BaoHanh, int SoLuongTon, String HinhAnh, int MaLoaiLinhKien, int MaNCC, boolean DaXoa) {
        this.MaLinhKien = MaLinhKien;
        this.TenLinhKien = TenLinhKien;
        this.XuatSu = XuatSu;
        this.GiaBan = GiaBan;
        this.BaoHanh = BaoHanh;
        this.SoLuongTon = SoLuongTon;
        this.HinhAnh = HinhAnh;
        this.MaLoaiLinhKien = MaLoaiLinhKien;
        this.MaNCC = MaNCC;
        this.DaXoa = DaXoa;
    }
    
}
