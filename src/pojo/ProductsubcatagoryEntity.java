package pojo;

import javax.persistence.*;

@Entity
@Table(name = "productsubcatagory", schema = "kson", catalog = "")
public class ProductsubcatagoryEntity {
    private long pscid;
    private String name;
    private int pcid;
    private String icon;

    @Id
    @Column(name = "pscid")
    public long getPscid() {
        return pscid;
    }

    public void setPscid(long pscid) {
        this.pscid = pscid;
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
    @Column(name = "pcid")
    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsubcatagoryEntity that = (ProductsubcatagoryEntity) o;

        if (pscid != that.pscid) return false;
        if (pcid != that.pcid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pscid ^ (pscid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + pcid;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }
}
