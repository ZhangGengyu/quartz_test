package feishu.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see java.util.List
 */
public class TestURL {

//    static final String APP_ACCESS_TOKEN_URL = "https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal/";
//    static final String USER_INFO_URL = "https://open.feishu.cn/open-apis/authen/v1/access_token";

    /**
     * @see feishu.urlencode.MyMain
     * @see feishu.urlencode.MyMain#see
     * @see "https://wwww.baidu.com"
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String url = "https://open.feishu.cn/open-apis/authen/v1/access_token";
        //构造测试数据
        //设置请求方式与参数
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpPost httpPost = new HttpPost(uri);
        httpPost.getParams().setParameter("http.socket.timeout", new Integer(500000));
        httpPost.setHeader("Content-type", "application/json; charset=UTF-8");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
        httpPost.setHeader("IConnection", "close");

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("KEY1", "VALUE1"));
        //...
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        //执行请求
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter("Content-Encoding", "UTF-8");
        HttpResponse response = httpclient.execute(httpPost);

        //获取返回
        HttpEntity entity = response.getEntity();
        BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String line = null;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        System.out.println(buffer.toString());

        //构造测试数据
//        JSONObject param = new JSONObject();
//        param.put("app_access_token", "t-383f3ccfcc280d059ce547d8ced606dee34e44dd");
//        param.put("grant_type", "authorization_code");
//        param.put("code", "fgHfpTIat5iVPfUxIko2fa");
//        //CloseableHttpClient：建立一个可以关闭的httpClient
//        //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
//        CloseableHttpClient client = HttpClients.createDefault();
//        //创建post请求
//        HttpPost post = new HttpPost(url);
//        //生成装载param的entity
//        StringEntity entity = new StringEntity(param.toString(), "utf-8");
//        entity.setContentType(ContentType.APPLICATION_JSON.toString());
//        post.setEntity(entity);
//        //执行请求
//        CloseableHttpResponse response = client.execute(post);
//        //返回string格式的结果
//        String result  = EntityUtils.toString(response.getEntity(), "utf-8");
//        System.out.println(result);
//        //关闭链接
//        post.releaseConnection();
//        client.close();
    }

    public static JSONObject httpPost(String url, Map<String, String> params) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        try {
            if (null != params) {
                // 构建消息实体 Map转json字符串
                StringEntity entity = new StringEntity(JSON.toJSONString(params), "UTF-8");
                entity.setContentEncoding("UTF-8");
                // 发送Json格式的数据请求
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return convertResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }
    private static JSONObject convertResponse(CloseableHttpResponse response) throws IOException {
        // 请求发送成功，并得到响应
        JSONObject jsonObject = new JSONObject();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 读取服务器返回过来的json字符串数据
            HttpEntity entity = response.getEntity();
            String strResult = EntityUtils.toString(entity, "UTF-8");
            // 把json字符串转换成json对象
            jsonObject = JSON.parseObject(strResult);
        }
        jsonObject.put("status",response.getStatusLine().getStatusCode());
        return jsonObject;
    }

    public static String sendHttpRequest(String url,String parameter,String contentType) throws UnsupportedEncodingException {
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent=null;
        HttpPost httpPost=new HttpPost(url);
        httpPost.setHeader("Content-Type","application/json");
        StringEntity entity=new StringEntity(parameter,"UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try{
            CloseableHttpResponse response=client.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity he=response.getEntity();
                respContent= EntityUtils.toString(he,"UTF-8");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return respContent;
    }

    public static String getResult(String url,Map<String, String> params){
        String returnValue = null;
        HttpClient httpclient = null;
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("Content-type","application/json; charset=utf-8");
            httppost.setHeader("Accept", "application/json");

            StringEntity entity = new StringEntity(JSONObject.toJSONString(params), Charset.forName("UTF-8"));
            httppost.setEntity(entity);

            HttpResponse resp = httpclient.execute(httppost);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                String   respContent = EntityUtils.toString(he,"UTF-8");
                returnValue =  respContent;
            }
        }
        catch (SocketTimeoutException e) {
            e.printStackTrace();
            returnValue = "timeout";
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            httpclient.getConnectionManager().shutdown();
        }
        return  returnValue;
    }

    public static void newUrl() {
        try {
            String url = "http://www.zuidaima.com/302.htm";
            System.out.println("访问地址:" + url);
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl
                    .openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);

            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "http://zuidaima.com/");
            conn.connect();
            String location = conn.getHeaderField("Location");

            serverUrl = new URL(location);
            conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");

            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "http://zuidaima.com/");
            conn.connect();
            System.out.println("跳转地址:" + location);

        } catch (Exception e) {
            e.printStackTrace();
        }
}

    /**
     * 带参数的get请求
     * @param url
     * @param param
     * @return String
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    public static String sendPost(String url, String jsonData) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            URLConnection con = realUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) con;
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type",
                    "application/json"); // 设置内容类型
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(
                    conn.getOutputStream(), "utf-8"));
            out.write(jsonData);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            byte[] bresult = result.toString().getBytes();
            result = new StringBuilder(new String(bresult, "utf-8"));
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String doPost(String url, String jsonData) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            URLConnection con = realUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) con;
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置内容类型
            conn.setRequestProperty("Content-Type",
                    "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(
                    conn.getOutputStream(), "utf-8"));
            out.write(jsonData);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            byte[] bresult = result.toString().getBytes();
            result = new StringBuilder(new String(bresult, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
