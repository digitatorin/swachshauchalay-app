package in.diyfix.swachsauchalay;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.GET;

import java.util.List;

public class ToiletApiService {
    
    public interface Server {
        @GET("/toilets")
        Call<GeoJson> getToilets();
    }
}
