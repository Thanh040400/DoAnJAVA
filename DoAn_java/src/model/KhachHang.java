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
public class KhachHang {

    private int MaKhachHang ;
    private String Hoten;
    private String GioiTinh ;
    private boolean DaXoa;
    
    public KhachHang(int MaKhachHang, String Hoten, String GioiTinh, boolean DaXoa) {
        this.MaKhachHang = MaKhachHang;
        this.Hoten = Hoten;
        this.GioiTinh = GioiTinh;
        this.DaXoa = DaXoa;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }
    
}
