package com.revature.apis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * EmotionAPI
 */
public final class EmotionAPI {

    private Properties props = new Properties();
    private String api_key;
    private OkHttpClient client = new OkHttpClient();
    private static EmotionAPI emotionAPI = new EmotionAPI();

    private EmotionAPI() {
        super();
        try {
            
            props.load(new FileReader("F:\\Code Projects\\Emotxt\\Server\\src\\main\\resources\\app.properties"));
            api_key = props.getProperty("emotion-api-key");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static EmotionAPI getInstance() {
        return emotionAPI;
    }

    // for single sentence
    public String emotionGuage(String text) {

        if(api_key == null) return null;
        
        String url = "https://apis.paralleldots.com/v5/emotion";
        
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("api_key", api_key).addFormDataPart("text", text).build();
        
        Request request = new Request.Builder().url(url).post(requestBody).addHeader("cache-control", "no-cache")
        .build();
    try {
        
        Response response = client.newCall(request).execute();
        return response.body().string();
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch (IOException ioe) {
        ioe.printStackTrace();
    }

    return null;
    
}

}