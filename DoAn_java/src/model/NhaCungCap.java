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
public class NhaCungCap {
    private int maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChiString;
    private String SDT;
    private boolean DaXoa;

    public NhaCungCap() {
    }
    
     public NhaCungCap(int maNhaCungCap, String tenNhaCungCap, String diaChiString, String SDT, boolean DaXoa) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChiString = diaChiString;
        this.SDT = SDT;
        this.DaXoa = DaXoa;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChiString() {
        return diaChiString;
    }

    public void setDiaChiString(String diaChiString) {
        this.diaChiString = diaChiString;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }
    
    
    
}
