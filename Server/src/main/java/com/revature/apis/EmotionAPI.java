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
public class EmotionAPI {

    private Properties props = new Properties();
    OkHttpClient client = new OkHttpClient();

    public EmotionAPI() {
        super();
        try {
			props.load(new FileReader("src/main/resources/app.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // for single sentence
    public String singleStringEmotion(String text) {

        try {
        String api_key = props.getProperty("emotion-api-key");
        if(api_key == null) return null;

        String url = "https://apis.paralleldots.com/v5/emotion";
        
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("api_key", api_key).addFormDataPart("text", text).build();

        Request request = new Request.Builder().url(url).post(requestBody).addHeader("cache-control", "no-cache")
        .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch (IOException ioe) {
        ioe.printStackTrace();
    }

    return null;
    
}
    
    public void method2 (String[] text){
        if(text == null || text.length == 0)
            return;
        // for multiple sentence as array
        String api_key = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String lang_code = "en";
        String url = "https://apis.paralleldots.com/v5/emotion_batch";
        OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("api_key", api_key)
            .addFormDataPart("text", text)
            .build();
            Request request = new Request.Builder()
              .url(url)
              .post(requestBody)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
    }
}