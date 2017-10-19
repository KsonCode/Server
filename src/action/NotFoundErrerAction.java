package action;

import com.opensymphony.xwork2.Action;

import java.util.HashMap;
import java.util.Map;

public class NotFoundErrerAction implements Action {
    public static int count = 0;//无效接口次数
    private Map<String,Object> jsonData;

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public String notFound() {
        count++;
        jsonData = new HashMap<>();
       jsonData.put("code","1");
       jsonData.put("msg","接口地址貌似不存在，我的天呢！好好检查一遍！！！");
        System.out.println("==============接口地址貌似不存在，我的天呢！好好检查一遍！！==============");
        System.out.println("无效接口调用："+count+"次");
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
