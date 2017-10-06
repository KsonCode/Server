package action;

import bean.AdEntity;
import bean.ProductEntity;
import bean.UserEntity;
import bean.ad.BAdBean;
import bean.ad.MAdBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.query.Query;
import service.UserImpl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdAction extends ActionSupport {

    private UserImpl user;
    private Map<String, Object> jsonData;

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public UserImpl getUser() {
        return user;
    }

    public String getAd() {
        jsonData = new HashMap<>();
        System.out.println("===============获取首页广告请求===============");

        List<AdEntity> list = getUser().getSf().openSession().createQuery("from AdEntity ").list();
        if (list != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", list);

        }

        String m = "39";
        String t = "40";
        MAdBean mAdBean = new MAdBean();
        mAdBean.setName("京东秒杀");
        mAdBean.setTime(1000 * 10 * 60 * 12);
        String sql = "from ProductEntity where pscid = :pscid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("pscid", Long.parseLong(m));

        List<ProductEntity> productEntityList = query.list();

//        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pscid = '39'").list();

        if (productEntityList != null && productEntityList.size() > 0) {
            mAdBean.setList(productEntityList);
        }
        jsonData.put("miaosha", mAdBean);

        BAdBean bAdBean = new BAdBean();
        bAdBean.setName("为你推荐");

        String sql2 = "from ProductEntity where pscid = :pscid";
        Query query1 = getUser().getSf().openSession().createQuery(sql2);
        query1.setParameter("pscid", Long.parseLong(t));
        List<ProductEntity> productEntityList1 = query1.list();

//        List<ProductEntity> productEntityList1 = getUser().getSf().openSession().createQuery("from ProductEntity where pscid = '40'").list();

        if (productEntityList1 != null && productEntityList1.size() > 0) {
            bAdBean.setList(productEntityList1);
        }
        jsonData.put("tuijian", bAdBean);

        System.out.println("===============获取首页广告成功===============");


        return SUCCESS;
    }


}
