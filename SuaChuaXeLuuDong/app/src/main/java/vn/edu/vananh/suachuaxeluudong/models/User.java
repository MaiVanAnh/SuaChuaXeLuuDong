package vn.edu.vananh.suachuaxeluudong.models;

import android.media.Image;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String tenTaiKhoan;
    private String matKhau;
    private String sdt;
    private String diaChi;
    private int namSinh;
    private boolean isThoSuaXe;
    private Double khoangCach;
    private Image image;

    public User() {
    }

    public User(String id, String tenTaiKhoan, String matKhau, String sdt, String diaChi, int namSinh, boolean isThoSuaXe) {
        this.id = id;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.namSinh = namSinh;
        this.isThoSuaXe = isThoSuaXe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public boolean isThoSuaXe() {
        return isThoSuaXe;
    }

    public void setThoSuaXe(boolean thoSuaXe) {
        isThoSuaXe = thoSuaXe;
    }

    public void setKhoangCach(double khoanCach) {
        this.khoangCach = khoanCach;
    }

    public double getKhoangCach() {
        return this.khoangCach;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", sdt='" + sdt + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", namSinh=" + namSinh +
                ", isThoSuaXe=" + isThoSuaXe +
                '}';
    }
}
