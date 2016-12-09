package antonioneto.unibratec.com.br.celebinfo.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import antonioneto.unibratec.com.br.celebinfo.model.CelebSearchResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;

/**
 * Created by AntonioNeto on 03/12/2016.
 */
public class CelebsParser {

    public static final String URL_SEARCH= "https://daxeel-celebinfo-v1.p.mashape.com/name/%s";

    public static List<Celeb> searchByTitle(String q) throws IOException {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder().url(String.format(URL_SEARCH,q))
            .header("X-Mashape-Key","xkeBF1hf8tmshzDrgRINcISC3U5Gp1x3ZbXjsna2WRICMOCC3U")
            .addHeader("Accept-Language","application/json")
            .build();


        Response response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK){
        String json = response.body().string();
        Gson gson = new Gson();
        CelebSearchResult result =
                gson.fromJson(json, CelebSearchResult.class);
        if(result != null){
            return result.celebs;
        }
        }
        return null;
    }


}
