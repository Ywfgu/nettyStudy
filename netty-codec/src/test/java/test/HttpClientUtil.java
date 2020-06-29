package test;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author guht
 * @create 2020/6/19
 * @Description
 */
public class HttpClientUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("hello","UTF-8"));

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:9999/spxm/spxm/v1/sms/send");
        httpPost.setHeader("X-Key","4DE376234154437AB218AF3B7DC2CDA3");
        String param = "{\"serviceId\":\"SIDXTA7635\",\"mobile\":\"18712345678\",\"content\":\"hello world\",\"outId\":\"\",\"smsUpExtendCode\":\"\"}";
        StringEntity entity = new StringEntity(param, "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
