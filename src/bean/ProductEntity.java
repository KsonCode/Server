package bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product", schema = "kson", catalog = "")
public class ProductEntity {
    private long pid;
    private String title;
    private String images;
    private String detailUrl;
    private Timestamp createtime;
    private double price;
    private String subhead;
    private long pscid;
    private Double bargainPrice;
    private long sellerid;
    private Integer salenum;

    @Id
    @Column(name = "pid")
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
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
    @Column(name = "images")
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Basic
    @Column(name = "detail_url")
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "subhead")
    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    @Basic
    @Column(name = "pscid")
    public long getPscid() {
        return pscid;
    }

    public void setPscid(long pscid) {
        this.pscid = pscid;
    }

    @Basic
    @Column(name = "bargain_price")
    public Double getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(Double bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    @Basic
    @Column(name = "sellerid")
    public long getSellerid() {
        return sellerid;
    }

    public void setSellerid(long sellerid) {
        this.sellerid = sellerid;
    }

    @Basic
    @Column(name = "salenum")
    public Integer getSalenum() {
        return salenum;
    }

    public void setSalenum(Integer salenum) {
        this.salenum = salenum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (pid != that.pid) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (pscid != that.pscid) return false;
        if (sellerid != that.sellerid) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (images != null ? !images.equals(that.images) : that.images != null) return false;
        if (detailUrl != null ? !detailUrl.equals(that.detailUrl) : that.detailUrl != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (subhead != null ? !subhead.equals(that.subhead) : that.subhead != null) return false;
        if (bargainPrice != null ? !bargainPrice.equals(that.bargainPrice) : that.bargainPrice != null) return false;
        if (salenum != null ? !salenum.equals(that.salenum) : that.salenum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (pid ^ (pid >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (detailUrl != null ? detailUrl.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (subhead != null ? subhead.hashCode() : 0);
        result = 31 * result + (int) (pscid ^ (pscid >>> 32));
        result = 31 * result + (bargainPrice != null ? bargainPrice.hashCode() : 0);
        result = 31 * result + (int) (sellerid ^ (sellerid >>> 32));
        result = 31 * result + (salenum != null ? salenum.hashCode() : 0);
        return result;
    }
}
