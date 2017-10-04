package bean.ad;

import bean.ProductEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 秒杀类
 */
public class MAdBean implements Serializable{

    private String name;
    private long time;
    private List<ProductEntity> list;

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

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
