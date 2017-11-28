package pojo.quarter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "workfavorite", schema = "kson", catalog = "")
public class WorkfavoriteEntity {
    private long fid;
    private long uid;
    private long wid;
    private Timestamp createTime;

    @Id
    @Column(name = "fid")
    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "wid")
    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkfavoriteEntity that = (WorkfavoriteEntity) o;

        if (fid != that.fid) return false;
        if (uid != that.uid) return false;
        if (wid != that.wid) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (fid ^ (fid >>> 32));
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (int) (wid ^ (wid >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
