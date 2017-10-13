package bean.study;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "images", schema = "kson", catalog = "")
public class ImagesEntity {
    private int id;
    private String imgurl;
    private Timestamp createtime;

    @Id
    @Column(name = "_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imgurl")
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

        ImagesEntity that = (ImagesEntity) o;

        if (id != that.id) return false;
        if (imgurl != null ? !imgurl.equals(that.imgurl) : that.imgurl != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imgurl != null ? imgurl.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }
}
