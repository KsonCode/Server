package pojo.quarter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "works", schema = "kson", catalog = "")
public class WorksEntity {
    private long wid;
    private String videoUrl;
    private String localUri;
    private String workDesc;
    private long uid;
    private Integer praiseNum;
    private Integer commentNum;
    private Timestamp createTime;
    private Long playNum;
    private String longitude;
    private String latitude;
    private String cover;
    private Integer favoriteNum;

    @Id
    @Column(name = "wid")
    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    @Basic
    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "local_uri")
    public String getLocalUri() {
        return localUri;
    }

    public void setLocalUri(String localUri) {
        this.localUri = localUri;
    }

    @Basic
    @Column(name = "work_desc")
    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
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
    @Column(name = "praise_num")
    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    @Basic
    @Column(name = "comment_num")
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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
    @Column(name = "play_num")
    public Long getPlayNum() {
        return playNum;
    }

    public void setPlayNum(Long playNum) {
        this.playNum = playNum;
    }

    @Basic
    @Column(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "favorite_num")
    public Integer getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(Integer favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorksEntity that = (WorksEntity) o;

        if (wid != that.wid) return false;
        if (uid != that.uid) return false;
        if (videoUrl != null ? !videoUrl.equals(that.videoUrl) : that.videoUrl != null) return false;
        if (localUri != null ? !localUri.equals(that.localUri) : that.localUri != null) return false;
        if (workDesc != null ? !workDesc.equals(that.workDesc) : that.workDesc != null) return false;
        if (praiseNum != null ? !praiseNum.equals(that.praiseNum) : that.praiseNum != null) return false;
        if (commentNum != null ? !commentNum.equals(that.commentNum) : that.commentNum != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (playNum != null ? !playNum.equals(that.playNum) : that.playNum != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (favoriteNum != null ? !favoriteNum.equals(that.favoriteNum) : that.favoriteNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (wid ^ (wid >>> 32));
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        result = 31 * result + (localUri != null ? localUri.hashCode() : 0);
        result = 31 * result + (workDesc != null ? workDesc.hashCode() : 0);
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (praiseNum != null ? praiseNum.hashCode() : 0);
        result = 31 * result + (commentNum != null ? commentNum.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (playNum != null ? playNum.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (favoriteNum != null ? favoriteNum.hashCode() : 0);
        return result;
    }
}
