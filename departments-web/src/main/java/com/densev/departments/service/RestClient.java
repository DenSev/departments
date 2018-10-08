package com.densev.departments.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.ws.rs.core.Response;
import java.io.IOException;


/**
 * Created on: 10/05/18
 */
@Component
public class RestClient {

    private final CloseableHttpClient client;
    private final HttpHost clientHost;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestClient(@Value("${rest.client.host}") String clientHostUrl,
                      @Value("${rest.client.port}") int port,
                      ObjectMapper objectMapper) {
        this.client = HttpClientBuilder
            .create()
            .build();
        this.clientHost = new HttpHost(clientHostUrl, port, "https");
        this.objectMapper = objectMapper;
    }

    @PreDestroy
    public void cleanUp() throws IOException {
        client.close();
    }

    public <T> T get(String url, TypeReference<T> responseType) throws IOException {
        HttpGet getRequest = new HttpGet(url);
        try (CloseableHttpResponse response = client.execute(clientHost, getRequest)) {
            if (Response.Status.OK.getStatusCode() != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException("REST api responded with non 200 http code!");
            }
            HttpEntity responseEntity = response.getEntity();
            T responseObject = objectMapper.readValue(responseEntity.getContent(), responseType);

            return responseObject;
        }
    }

    public <T> T post(String url, Object body, TypeReference<T> responseType) throws IOException {
        HttpPost postRequest = new HttpPost(url);
        String stringBody = objectMapper.writeValueAsString(body);
        postRequest.setEntity(new StringEntity(stringBody));
        try(CloseableHttpResponse response = client.execute(clientHost, postRequest)) {
            if(Response.Status.OK.getStatusCode() != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException("REST api responded with non 200 http code!");
            }

            HttpEntity responseEntity = response.getEntity();
            T responseObject = objectMapper.readValue(responseEntity.getContent(), responseType);

            return responseObject;
        }
    }

}
