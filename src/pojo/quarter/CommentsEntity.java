package pojo.quarter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments", schema = "kson", catalog = "")
public class CommentsEntity {
    private int cid;
    private long uid;
    private String content;
    private Timestamp createTime;
    private int praiseNum;
    private Integer mvp;
    private Long wid;
    private Long eid;

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "praise_num")
    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    @Basic
    @Column(name = "mvp")
    public Integer getMvp() {
        return mvp;
    }

    public void setMvp(Integer mvp) {
        this.mvp = mvp;
    }

    @Basic
    @Column(name = "wid")
    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    @Basic
    @Column(name = "eid")
    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsEntity that = (CommentsEntity) o;

        if (cid != that.cid) return false;
        if (uid != that.uid) return false;
        if (praiseNum != that.praiseNum) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (mvp != null ? !mvp.equals(that.mvp) : that.mvp != null) return false;
        if (wid != null ? !wid.equals(that.wid) : that.wid != null) return false;
        if (eid != null ? !eid.equals(that.eid) : that.eid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + praiseNum;
        result = 31 * result + (mvp != null ? mvp.hashCode() : 0);
        result = 31 * result + (wid != null ? wid.hashCode() : 0);
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        return result;
    }
}
