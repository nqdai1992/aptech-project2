package sample.entity;

import javax.persistence.*;

/**
 * Created by dainguyen on 1/1/19.
 */
@Entity
@Table(name = "nhanvien", schema = "supermaketdb", catalog = "")
public class NhanvienEntity {
    private int maNv;
    private String tenNv;
    private String diaChi;
    private String username;
    private String password;

    @Id
    @Column(name = "maNV")
    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    @Basic
    @Column(name = "tenNV")
    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NhanvienEntity that = (NhanvienEntity) o;

        if (maNv != that.maNv) return false;
        if (tenNv != null ? !tenNv.equals(that.tenNv) : that.tenNv != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maNv;
        result = 31 * result + (tenNv != null ? tenNv.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
