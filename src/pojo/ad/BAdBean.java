package pojo.ad;

import pojo.ProductEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 首页为你推荐
 */
public class BAdBean implements Serializable{
    private String name;

    private List<ProductEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getList() {
        return list;
    }

    public void setList(List<ProductEntity> list) {
        this.list = list;
    }
}
