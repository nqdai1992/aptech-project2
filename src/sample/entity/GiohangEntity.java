package sample.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by dainguyen on 1/1/19.
 */
@Entity
@Table(name = "giohang", schema = "supermaketdb", catalog = "")
public class GiohangEntity implements Serializable {
    private int maGh;
    private int maHd;
    private int maSp;
    private int soLuong;

    @Id
    @Column(name = "maGH")
    public int getMaGh() {
        return maGh;
    }

    public void setMaGh(int maGh) {
        this.maGh = maGh;
    }

    @Basic
    @Column(name = "maHD")
    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    @Basic
    @Column(name = "maSP")
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "soLuong")
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiohangEntity that = (GiohangEntity) o;

        if (maGh != that.maGh) return false;
        if (maHd != that.maHd) return false;
        if (maSp != that.maSp) return false;
        if (soLuong != that.soLuong) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGh;
        result = 31 * result + maHd;
        result = 31 * result + maSp;
        result = 31 * result + soLuong;
        return result;
    }
}
