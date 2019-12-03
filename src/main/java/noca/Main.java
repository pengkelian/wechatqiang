package noca;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

        String url = "https://house-on-sale.focus.cn/api/wap/mini/login/getUserProjectList?phone=&idCard=430381198810014174&realName=%E5%AE%8B%E7%92%90&loginType=1&sign=a8db950321dfdb8155d1d61ed3bc1bf0";
        try (CloseableHttpClient httpClient = createHttpClient()) {
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                
                System.out.printf(result);
            }
        }
    }


    private static CloseableHttpClient createHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, null, null,
                new NoopHostnameVerifier());

        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
    }
}