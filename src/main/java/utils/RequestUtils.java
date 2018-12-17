package utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class RequestUtils {

    public static String getResult(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();

        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
