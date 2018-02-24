package com.nicole.web;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiads
 * @date 11/01/2018
 * @since
 */
public class HttpUtil {

    public static String get(String url) {
        return get(url, false, null);
    }

    public static String get(String url, Header... headers) {
        return get(url, false, headers);
    }

    public static String get(String url, boolean useProxy, Header[] headers) {
        // 1. 创建一个默认的client实例
        CloseableHttpClient client;
        if (useProxy) {
            HttpHost proxy = new HttpHost("127.0.0.1", 8888);// 设置代理ip
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            client = HttpClients.custom().setRoutePlanner(routePlanner).build();
        } else {
            client = HttpClients.createDefault();
        }
        try {
            // 2. 创建一个httpget对象
            HttpGet httpGet = new HttpGet(url);
            if (headers == null) {
                httpGet.addHeader("Content-Type", "text/html; charset=utf-8");
            } else {
                httpGet.setHeaders(headers);
            }
            headers = httpGet.getAllHeaders();
            System.out.println("-------------------------------------------");
            for (Header header : headers) {
                System.out.println(header);
            }
            //System.out.println("executing GET request " + httpGet.getURI());
            // 3. 执行GET请求并获取响应对象
            CloseableHttpResponse resp = client.execute(httpGet);
            try {
                // 4. 获取响应体
                HttpEntity entity = resp.getEntity();
                //System.out.println("-------------------------------------------");
                // 5. 打印响应状态
                //System.out.println(resp.getStatusLine());
                headers = resp.getAllHeaders();
                for (Header header : headers) {
                    //System.out.println(header);
                }
                // 6. 打印响应长度和响应内容
                if (null != entity) {
                    String html = EntityUtils.toString(entity);
                    //System.out.println("Response content length = " + entity.getContentLength());
                    //System.out.println("Response content is:\n" + html);
                    return html;
                }
                //System.out.println("------");
            } finally {
                // 7. 无论请求成功与否都要关闭resp
                resp.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 8. 最终要关闭连接，释放资源
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<NameValuePair> createParams(Map<String, String> params) {
        Set<Map.Entry<String, String>> paramsEntrySet = params.entrySet();
        List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramsEntrySet) {
            paramPairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return paramPairs;
    }

    public static String post(String url, Map<String, String> params) {
        // 1. 获取默认的client实例
        CloseableHttpClient client = HttpClients.createDefault();
        // 2. 创建httppost实例
        HttpPost httpPost = new HttpPost(url);
        // 3. 创建参数队列（键值对列表）
        List<NameValuePair> paramPairs = createParams(params);
        //httpPost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        UrlEncodedFormEntity entity;
        try {
            // 4. 将参数设置到entity对象中
            entity = new UrlEncodedFormEntity(paramPairs, "UTF-8");
            // 5. 将entity对象设置到httppost对象中
            httpPost.setEntity(entity);
            System.out.println("executing POST request " + httpPost.getURI());
            // 6. 发送请求并回去响应
            CloseableHttpResponse resp = client.execute(httpPost);
            try {
                // 7. 获取响应entity
                HttpEntity respEntity = resp.getEntity();
                // 8. 打印出响应内容
                if (null != respEntity) {
                    String html = EntityUtils.toString(respEntity, "UTF-8");
                    System.out.println("------");
                    System.out.println(resp.getStatusLine());
                    System.out.println("Response content is : \n" + html);
                    System.out.println("------");
                    return html;
                }
            } finally {
                // 9. 关闭响应对象
                resp.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 10. 关闭连接，释放资源
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(String url, String params) {
        return post(url, params, null);
    }

    public static String post(String url, String params, Header... headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.length > 0) {
            httpPost.setHeaders(headers);
        }
        try {
            StringEntity se = new StringEntity(params);
            httpPost.setEntity(se);

            System.out.println("-------------------------------------------");
            for (Header header : headers) {
                System.out.println(header);
            }

            CloseableHttpResponse resp = client.execute(httpPost);
            try {
                HttpEntity respEntity = resp.getEntity();
                if (null != respEntity) {
                    return EntityUtils.toString(respEntity, "UTF-8");
                }
            } finally {
                resp.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
