package com.zhang.colas.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private static PoolingHttpClientConnectionManager poolManager = null;
    private static final int maxTotalPool = 50;
    private static final int maxConPerRoute = 20;
    private static final int socketTimeout = 2000;
    private static final int connectionRequestTimeout = 3000;
    private static final int connectTimeout = 1000;

    static {
        try {
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslsf)
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .build();
            poolManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolManager.setMaxTotal(maxTotalPool);
            poolManager.setDefaultMaxPerRoute(maxConPerRoute);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(socketTimeout).build();
            poolManager.setDefaultSocketConfig(socketConfig);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static CloseableHttpClient getConnection() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        if (poolManager != null && poolManager.getTotalStats() != null) {
            System.out.println("now client pool " + poolManager.getTotalStats().toString());
        }
        return httpClient;
    }

    public static String doSimplePost(String url, HashMap params) {
        String returnStr = null;
        if (StringUtils.isBlank(url)) {
            return returnStr;
        }

        HttpPost httpPost = new HttpPost(url);
        try {
            List<NameValuePair> nvps = new ArrayList<>();
            if (params != null && !params.isEmpty()) {
                NameValuePair nvp;
                for (Object key :
                        params.keySet()) {
                    nvp = new BasicNameValuePair(String.valueOf(key), String.valueOf(params.get(key)));
                    nvps.add(nvp);
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            CloseableHttpClient httpClient = getConnection();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                String res = "";
                if (entity != null) {
                    res = EntityUtils.toString(entity, "utf-8");
                }
                LOGGER.info("接收响应：url=" + url + ";status=" + status);
                return entity != null ? res : null;
            } else {
                HttpEntity entity = response.getEntity();
                httpPost.abort();
                LOGGER.info("接收响应：url=" + url + ";status=" + status + ";response=" + EntityUtils.toString(entity, "utf-8"));
                throw new ClientProtocolException("Unexpected response status:" + status);
            }
        } catch (Exception e) {
            httpPost.abort();
            LOGGER.error(e.getMessage(), e);
        }
        return returnStr;

    }

    public static String doSimpleGet(String url) {

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = getConnection();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity,"utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
