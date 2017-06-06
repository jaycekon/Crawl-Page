//package com;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.AuthCache;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.EntityBuilder;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.entity.ContentType;
//import org.apache.http.impl.auth.BasicScheme;
//import org.apache.http.impl.client.*;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//
//
///**
// * Created by Administrator on 2017/6/1.
// */
//public class Test {
//    public static final String URL = "http://10.202.1.13:5601/elasticsearch/_msearch?timeout=0&ignore_unavailable=true";
//    public static final String CONTEXT = "{\"index\":[\"kn-operator-server-*\"],\"search_type\":\"count\",\"ignore_unavailable\":true}\\n{\"size\":0,\"query\":{\"filtered\":{\"query\":{\"query_string\":{\"query\":\"*\",\"analyze_wildcard\":true}},\"filter\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":1496283600930,\"lte\":1496284500930,\"format\":\"epoch_millis\"}}}],\"must_not\":[]}}}},\"aggs\":{\"2\":{\"terms\":{\"field\":\"context.sessionId.raw\",\"size\":0,\"order\":{\"1\":\"desc\"}},\"aggs\":{\"1\":{\"avg\":{\"field\":\"context.costTime_long\"}},\"3\":{\"terms\":{\"field\":\"context.entrance.raw\",\"size\":0,\"order\":{\"1\":\"desc\"}},\"aggs\":{\"1\":{\"avg\":{\"field\":\"context.costTime_long\"}}}}}}}}\\n";
//
//
//    public static void main(String[] args) throws IOException {
//
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        // HttpClient
//        CloseableHttpClient httpClient = httpClientBuilder.build();
//
//        HttpPost httpPost = new HttpPost(URL);
//
//        httpPost.addHeader("Authorization", "Basic a24tb3BlcmF0b3I6eUpOQTRRWWUya2ZNVVV4RA==");
//        httpPost.addHeader("Proxy-Authorization", "Basic Y2Fpamlud3U6MjRod2Fi");
//        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
//        httpPost.addHeader("kbn-version", "4.6.4");
//        HttpEntity entity = EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON)
//                .setContentEncoding("UTF-8").setText(CONTEXT).build();
//        httpPost.setEntity(entity);
//        HttpContext context = setProxyIpAndTimeOut(httpPost,"172.22.23.242",80,"caijinwu", "24hwab",20);
//        CloseableHttpResponse response1 = httpClient.execute(httpPost,context);
//        try {
//            System.out.println(response1.getStatusLine());
//            HttpEntity entity1 = response1.getEntity();
//            byte[] bytes = new byte[1024*1024];
//            entity1.getContent().read(bytes);
//            System.out.println(new String(bytes));
//            EntityUtils.consume(entity1);
//        } finally {
//            response1.close();
//            httpClient.close();
//        }
//    }
//
//
//    private static HttpContext setProxyIpAndTimeOut(HttpRequestBase method, String ip,int port,String username,String paswd, int timeout){
//        HttpContext httpContext = null;
//        RequestConfig.Builder builder = RequestConfig.custom()
//                .setSocketTimeout(timeout*1000).setConnectTimeout(timeout*1000);//设置请求和传输超时时间;
//        if(ip != null){
//            HttpHost proxy = new HttpHost(ip,port);
//            builder.setProxy(proxy);
//            if (username != null && paswd !=null){
//                builder.setAuthenticationEnabled(true);
//                httpContext = new BasicHttpContext();
//                CredentialsProvider credsProvider = new BasicCredentialsProvider();
//                credsProvider.setCredentials(
//                        new AuthScope(ip, port),
//                        new UsernamePasswordCredentials(username,paswd));
//                httpContext.setAttribute(HttpClientContext.CREDS_PROVIDER, credsProvider);
//            }
//        }
//        method.setConfig(builder.build());
//        return httpContext;
//    }
//}
