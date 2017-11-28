package pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ad", schema = "kson", catalog = "")
public class AdEntity {
    private long aid;
    private String title;
    private Integer type;
    private String productId;
    private String icon;
    private String url;
    private Timestamp createtime;

    @Id
    @Column(name = "aid")
    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "productID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        AdEntity adEntity = (AdEntity) o;

        if (aid != adEntity.aid) return false;
        if (title != null ? !title.equals(adEntity.title) : adEntity.title != null) return false;
        if (type != null ? !type.equals(adEntity.type) : adEntity.type != null) return false;
        if (productId != null ? !productId.equals(adEntity.productId) : adEntity.productId != null) return false;
        if (icon != null ? !icon.equals(adEntity.icon) : adEntity.icon != null) return false;
        if (url != null ? !url.equals(adEntity.url) : adEntity.url != null) return false;
        if (createtime != null ? !createtime.equals(adEntity.createtime) : adEntity.createtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (aid ^ (aid >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }
}
