package sample.entity;

import javax.persistence.*;

/**
 * Created by dainguyen on 1/1/19.
 */
@Entity
@Table(name = "danhmuc", schema = "supermaketdb", catalog = "")
public class DanhmucEntity {
    private int ma;
    private String tenDanhMuc;
    private String description;

    @Id
    @Column(name = "ma")
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    @Basic
    @Column(name = "tenDanhMuc")
    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DanhmucEntity that = (DanhmucEntity) o;

        if (ma != that.ma) return false;
        if (tenDanhMuc != null ? !tenDanhMuc.equals(that.tenDanhMuc) : that.tenDanhMuc != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ma;
        result = 31 * result + (tenDanhMuc != null ? tenDanhMuc.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
