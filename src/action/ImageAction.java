package action;

import bean.AdEntity;
import bean.ProductEntity;
import bean.ad.BAdBean;
import bean.ad.MAdBean;
import bean.study.ImagesEntity;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.query.Query;
import service.UserImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  com.opensymphony.xwork2.Action;

public class ImageAction implements Action {

    private int page = 1;
    private UserImpl user;
    private Map<String, Object> jsonData;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

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

    public String getImages() {
        jsonData = new HashMap<>();
        System.out.println("===============获取图片请求===============");

        String sql = "from ImagesEntity";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setMaxResults(10);
        System.out.println("page:" + page);
        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult((page - 1) * 10);
        }

        List<ImagesEntity> list = query.list();
        if (list != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", list);

        }



        System.out.println("===============获取图片成功===============");


        return SUCCESS;
    }


    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
