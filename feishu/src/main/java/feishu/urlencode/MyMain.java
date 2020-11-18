package feishu.urlencode;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyMain {
    public String see = null;
    public static void main(String[] args) {
        String str = "https://open.feishu.cn/app/cli_9f23bc1b85b1d00e/baseinfo?a=中文";
        String result = GetRealUrl(str);
        System.out.println(result);
    }

    public static String getResult(String url,Map<String, String> params){
        String returnValue = null;
        HttpClient httpclient = new DefaultHttpClient();
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

    //对url中的参数进行url编码
    public static String GetRealUrl(String str) {
        try {
            int index = str.indexOf("?");
            if (index < 0) {
                return str;
            }
            String query = str.substring(0, index);
            String params = str.substring(index + 1);
            Map map = GetArgs(params);
            String encodeParams = TransMapToString(map);
            return query + "?" + encodeParams;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    //将url参数格式转化为map
    public static Map GetArgs(String params) throws Exception {
        Map map = new HashMap();
        String[] pairs = params.split("&");
        for (int i = 0; i < pairs.length; i++) {
            int pos = pairs[i].indexOf("=");
            if (pos == -1) {
                continue;
            }
            String argname = pairs[i].substring(0, pos);
            String value = pairs[i].substring(pos + 1);
            value = URLEncoder.encode(value, "utf-8");
            map.put(argname, value);
        }
        return map;
    }

    //将map转化为指定的String类型
    public static String TransMapToString(Map map) {
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (java.util.Map.Entry) iterator.next();
            sb.append(entry.getKey().toString()).append("=").append(null == entry.getValue() ? "" :
                    entry.getValue().toString()).append(iterator.hasNext() ? "&" : "");
        }
        return sb.toString();
    }
}
