package pojo.quarter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "follow", schema = "kson", catalog = "")
public class FollowEntity {
    private long fid;
    private long uid;
    private long followId;
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
    @Column(name = "follow_id")
    public long getFollowId() {
        return followId;
    }

    public void setFollowId(long followId) {
        this.followId = followId;
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

        FollowEntity that = (FollowEntity) o;

        if (fid != that.fid) return false;
        if (uid != that.uid) return false;
        if (followId != that.followId) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (fid ^ (fid >>> 32));
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (int) (followId ^ (followId >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
