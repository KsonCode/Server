package bean;

import javax.persistence.*;

@Entity
@Table(name = "addr", schema = "kson", catalog = "")
public class AddrEntity {
    private long addrid;
    private String name;
    private long mobile;
    private String addr;
    private int status;
    private long uid;

    @Id
    @Column(name = "addrid")
    public long getAddrid() {
        return addrid;
    }

    public void setAddrid(long addrid) {
        this.addrid = addrid;
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
    @Column(name = "mobile")
    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddrEntity that = (AddrEntity) o;

        if (addrid != that.addrid) return false;
        if (mobile != that.mobile) return false;
        if (status != that.status) return false;
        if (uid != that.uid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (addrid ^ (addrid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (mobile ^ (mobile >>> 32));
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        return result;
    }
}
