package sample.entity;

import javax.persistence.*;

/**
 * Created by dainguyen on 1/1/19.
 */
@Entity
@Table(name = "khachhang", schema = "supermaketdb", catalog = "")
public class KhachhangEntity {
    private int maKh;
    private String tenKh;
    private String diaChi;
    private int soDienThoai;

    @Id
    @Column(name = "maKH")
    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    @Basic
    @Column(name = "tenKH")
    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    @Basic
    @Column(name = "diaChi")
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Basic
    @Column(name = "soDienThoai")
    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KhachhangEntity that = (KhachhangEntity) o;

        if (maKh != that.maKh) return false;
        if (soDienThoai != that.soDienThoai) return false;
        if (tenKh != null ? !tenKh.equals(that.tenKh) : that.tenKh != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maKh;
        result = 31 * result + (tenKh != null ? tenKh.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + soDienThoai;
        return result;
    }
}
