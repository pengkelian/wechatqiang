package noca;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [Project]:whymoy  <br/>
 * [Email]:moy25@foxmail.com  <br/>
 * [Date]:2018/3/14  <br/>
 * [Description]:  <br/>
 *
 * @author YeXiangYang
 */
public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String path = "https://house-on-sale.focus.cn/api/wap/mini/login/userLoginByIdCard";

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("projectId","2338413324"));
        nvps.add(new BasicNameValuePair("realName","%E5%AE%8B%E7%92%90"));
        nvps.add(new BasicNameValuePair("idCard","140108198803104828"));
        nvps.add(new BasicNameValuePair("sign","16b215fb6731cc774c4cc3b271018c2b"));

        httpPOST(path,nvps);

        String url = "https://house-on-sale.focus.cn/subscribe/4354127744/homepage";
        httpGet(url);


    }


    private static CloseableHttpClient createHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, null, null,
                new NoopHostnameVerifier());

        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
    }

    public static void httpGet(String url) {
        try{
            CloseableHttpClient httpClient = createHttpClient();
            HashMap<String, String> headers = new HashMap<String, String>();

            headers.put("Referer", "https://servicewechat.com/wxfb1955c7ce7fc4b5/26/page-frame.html");
            headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.3(0x17000321) NetType/WIFI Language/zh_CN ");
            headers.put("Accept","*/*");
            headers.put("Accept-Language","zh-cn");
            headers.put("Host","house-on-sale.focus.cn");
            headers.put("Accept-Charset","utf-8;q=0.7,*;q=0.7");
            headers.put("Connection","keep-alive");
            headers.put("Content-Type","application/x-www-form-urlencoded");
            headers.put("Cache-Control","max-age=0");
            headers.put("Cookie","87a4bcbf0b1ea517_gr_session_id=963cb87d-541d-4f9b-bd76-30bff8642d75; 87a4bcbf0b1ea517_gr_session_id_963cb87d-541d-4f9b-bd76-30bff8642d75=true; gr_user_id=94e9cda8-97ea-4955-916d-4ea792a008d3; sale_user_id=9354678420; sale_user_token=6b6cab50c6efaeae790eb82e4b6ae91b; focus_login_captcha=d487c2e1-bc0a-450e-a1cf-655896be318d; grwng_uid=0f8d32ea-b031-4ce7-8a6d-b7c0dbe4bd9b");

            HttpGet httpGet = new HttpGet(url);

            for(Map.Entry<String, String> entry: headers.entrySet())
            {
                System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity,"UTF-8");
                EntityUtils.consume(entity);
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (
              Exception e
        ){
            e.printStackTrace();
        }
    }


    public static void httpPOST(String url, List<NameValuePair> params) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Referer", "https://servicewechat.com/wxfb1955c7ce7fc4b5/26/page-frame.html");
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.3(0x17000321) NetType/WIFI Language/zh_CN ");
        headers.put("Accept","*/*");
        headers.put("Accept-Language","zh-cn");
        headers.put("Host","house-on-sale.focus.cn");
        headers.put("Accept-Charset","utf-8;q=0.7,*;q=0.7");
        headers.put("Connection","keep-alive");
        headers.put("Content-Type","application/x-www-form-urlencoded");

        try{
            CloseableHttpClient httpClient = createHttpClient();
            HttpPost httpPost = new HttpPost(url);

            for(Map.Entry<String, String> entry: headers.entrySet())
            {
                System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
                httpPost.addHeader(entry.getKey(),entry.getValue());
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}