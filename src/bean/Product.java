package bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


public class Product implements Serializable{
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
    private int num;
    private int selected;

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getSelected() {
        return selected;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }


    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }


    public long getPscid() {
        return pscid;
    }

    public void setPscid(long pscid) {
        this.pscid = pscid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        if (pid != that.pid) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (pscid != that.pscid) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (images != null ? !images.equals(that.images) : that.images != null) return false;
        if (detailUrl != null ? !detailUrl.equals(that.detailUrl) : that.detailUrl != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (subhead != null ? !subhead.equals(that.subhead) : that.subhead != null) return false;

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
        return result;
    }

    public Double getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(Double bargainPrice) {
        this.bargainPrice = bargainPrice;
    }


    public long getSellerid() {
        return sellerid;
    }

    public void setSellerid(long sellerid) {
        this.sellerid = sellerid;
    }
}
