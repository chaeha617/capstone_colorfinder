package com.example.colorfinder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ImageUploader {

    public String uploadImage(byte[] imageData, String fileName) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            String endpointUrl = "http://localhost:5555/analyze";
            HttpEntity entity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", imageData, ContentType.DEFAULT_BINARY, fileName)
                    .build();

            HttpPost httpPost = new HttpPost(endpointUrl);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            return EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to upload image\"}";
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
