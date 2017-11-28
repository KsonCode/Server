package action;

import pojo.VersionEntity;
import com.opensymphony.xwork2.Action;
import org.hibernate.query.Query;
import service.UserImpl;
import utils.AccountValidatorUtil;
import utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionAction implements Action {

    private UserImpl user;

    private Map<String, Object> jsonData;
    private String  type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public String getVersion(){

        jsonData = new HashMap<>();

        if (Utils.isEmpty(type)){
            jsonData.put("code","1");
            jsonData.put("msg","type不能为空");
            return SUCCESS;
        }

        if (!AccountValidatorUtil.isInteger(type)){
            jsonData.put("code","1");
            jsonData.put("msg","type必须是整型");
            return SUCCESS;
        }

        String sql = "from VersionEntity where type = :type";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("type", Integer.parseInt(type));
        List<VersionEntity> versionEntities = query.list();

        if (versionEntities!=null&&versionEntities.size()>0){
            jsonData.put("code","0");
            jsonData.put("msg","获取版本信息成功");
            jsonData.put("data",versionEntities.get(0));
            type = null;
            return SUCCESS;
        }

        type = null;
        return SUCCESS;

    }
    @Override
    public String execute() throws Exception {
        return null;
    }
}
