package bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "catagory", schema = "kson", catalog = "")
public class CatagoryEntity {
    private long cid;
    private String name;
    private String icon;
    private byte ishome;
    private Timestamp createtime;

    @Id
    @Column(name = "cid")
    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "ishome")
    public byte getIshome() {
        return ishome;
    }

    public void setIshome(byte ishome) {
        this.ishome = ishome;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatagoryEntity that = (CatagoryEntity) o;

        if (cid != that.cid) return false;
        if (ishome != that.ishome) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cid ^ (cid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (int) ishome;
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }
}
