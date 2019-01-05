package sample.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

/**
 * Created by dainguyen on 1/1/19.
 */
@Entity
@Table(name = "hoadon", schema = "supermaketdb", catalog = "")
public class HoadonEntity implements Serializable {
    private int maHd;
    private Date ngayTaoHd;
    private int maNv;
    private int maKh;

    @Id
    @Column(name = "maHD")
    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    @Basic
    @Column(name = "ngayTaoHD")
    public Date getNgayTaoHd() {
        return ngayTaoHd;
    }

    public void setNgayTaoHd(Date ngayTaoHd) {
        this.ngayTaoHd = ngayTaoHd;
    }

    @Basic
    @Column(name = "maNV")
    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    @Basic
    @Column(name = "maKH")
    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HoadonEntity that = (HoadonEntity) o;

        if (maHd != that.maHd) return false;
        if (maNv != that.maNv) return false;
        if (maKh != that.maKh) return false;
        if (ngayTaoHd != null ? !ngayTaoHd.equals(that.ngayTaoHd) : that.ngayTaoHd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maHd;
        result = 31 * result + (ngayTaoHd != null ? ngayTaoHd.hashCode() : 0);
        result = 31 * result + maNv;
        result = 31 * result + maKh;
        return result;
    }
}
