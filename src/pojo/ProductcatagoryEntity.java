package pojo;

import javax.persistence.*;

@Entity
@Table(name = "productcatagory", schema = "kson", catalog = "")
public class ProductcatagoryEntity {
    private long pcid;
    private long cid;
    private String name;

    @Id
    @Column(name = "pcid")
    public long getPcid() {
        return pcid;
    }

    public void setPcid(long pcid) {
        this.pcid = pcid;
    }

    @Basic
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductcatagoryEntity that = (ProductcatagoryEntity) o;

        if (pcid != that.pcid) return false;
        if (cid != that.cid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pcid ^ (pcid >>> 32));
        result = 31 * result + (int) (cid ^ (cid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
