package action.api;


import com.opensymphony.xwork2.Action;
import common.Api;
import common.Constants;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import service.UserImpl;

import java.util.Map;

/**
 * 天气api，action
 */
public class WeatherAction implements Action {

    private String key;
    private String city;
    private String extensions;
    private String output;


    private UserImpl user;
    private Map<String, Object> jsonData;

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public String query(){



//        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.GAODE_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//                Api api = retrofit.create(Api.class);
//                Observable call = api.getData(Constants.GAODE_WEB_APPKEY,"北京");

//                call.



        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }
}
