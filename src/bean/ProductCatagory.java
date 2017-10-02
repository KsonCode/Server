package bean;

import java.io.Serializable;
import java.util.List;

public class ProductCatagory implements Serializable{
    private String name;
    private String pcid;
    private String cid;
    private List<ProductsubcatagoryEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<ProductsubcatagoryEntity> getList() {
        return list;
    }

    public void setList(List<ProductsubcatagoryEntity> list) {
        this.list = list;
    }
}
