package action.quarter;

import com.opensymphony.xwork2.Action;
import org.hibernate.query.Query;
import pojo.AdEntity;
import pojo.ProductEntity;
import pojo.ad.BAdBean;
import pojo.ad.MAdBean;
import service.UserImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdAction implements Action {

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

        return SUCCESS;
    }


    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
