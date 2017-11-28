package common;

import pojo.weather.WeatherEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    //天气查询
    //http://restapi.amap.com/v3/weather/weatherInfo?key=c80e57e9890b4e2eccb28034b329f1f9&city=%E5%8C%97%E4%BA%AC&extensions=all&output=json

    @GET("weather/weatherInfo")
    Observable<WeatherEntity> getData(@Query("key") String key,@Query("city") String city);
}
