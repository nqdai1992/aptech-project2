package sample.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by dainguyen on 1/1/19.
 */
@Entity
@Table(name = "sanpham", schema = "supermaketdb", catalog = "")
public class SanphamEntity implements Serializable {
    private int maSp;
    private String tenSp;
    private int price;
    private int categoryId;
    private String description;

    @Id
    @Column(name = "maSP")
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "tenSP")
    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

        SanphamEntity that = (SanphamEntity) o;

        if (maSp != that.maSp) return false;
        if (price != that.price) return false;
        if (categoryId != that.categoryId) return false;
        if (tenSp != null ? !tenSp.equals(that.tenSp) : that.tenSp != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maSp;
        result = 31 * result + (tenSp != null ? tenSp.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + categoryId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
