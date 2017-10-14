package action;

import com.opensymphony.xwork2.Action;

import java.util.HashMap;
import java.util.Map;

public class NotFoundErrerAction implements Action {
    private Map<String,Object> jsonData;

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public String notFound() {
        jsonData = new HashMap<>();
       jsonData.put("code","1");
       jsonData.put("msg","接口地址貌似不存在，我的天呢！好好检查一遍！！！");
        System.out.println("==============接口地址貌似不存在，我的天呢！好好检查一遍！！==============");
       return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
