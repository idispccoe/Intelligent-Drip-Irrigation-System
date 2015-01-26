package edu.pccoe.idis.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpRequestTask extends AsyncTask<String, Object, ServerResponse> {
    RestTemplate restTemplate = new RestTemplate();
    @Override
    protected ServerResponse doInBackground(String... params) {
        try {
            final String url = "http://192.168.1.4:8080/"+params[0];
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ServerResponse response = restTemplate.getForObject(url, ServerResponse.class);
            return response;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}