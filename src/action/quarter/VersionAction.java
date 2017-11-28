package action.quarter;

import com.opensymphony.xwork2.Action;
import org.hibernate.query.Query;
import pojo.VersionEntity;
import service.UserImpl;
import service.quarter.QuarterServiceImpl;
import utils.AccountValidatorUtil;
import utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionAction implements Action {

    private QuarterServiceImpl quarterService;


    private Map<String, Object> jsonData;


    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }


    public QuarterServiceImpl getQuarterService() {
        return quarterService;
    }

    public void setQuarterService(QuarterServiceImpl quarterService) {
        this.quarterService = quarterService;
    }

    public String getVersion(){

        jsonData = new HashMap<>();


        String sql = "from VersionEntity ";
        Query query =quarterService.getSf().openSession().createQuery(sql);
        List<VersionEntity> versionEntities = query.list();

        if (versionEntities!=null&&versionEntities.size()>0){
            jsonData.put("code","0");
            jsonData.put("msg","获取版本信息成功");
            jsonData.put("data",versionEntities.get(0));

            return SUCCESS;
        }

        return SUCCESS;

    }
    @Override
    public String execute() throws Exception {
        return null;
    }
}
