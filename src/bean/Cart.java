package bean;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable{
    private String sellerName;
    private String sellerid;
    private List<ProductEntity> list;


    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public List<ProductEntity> getList() {
        return list;
    }

    public void setList(List<ProductEntity> list) {
        this.list = list;
    }
}
