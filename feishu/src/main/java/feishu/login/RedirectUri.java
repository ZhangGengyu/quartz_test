package feishu.login;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class RedirectUri {
    @Test
    public void test_getRedirectUrl() throws Exception {
        String url="https://open.feishu.cn/open-apis/authen/v1/user_auth_page?app_id=cli_9f23bc1b85b1d00e&redirect_uri=https%3A%2F%2Fwww.sogou.com%2F&state=";
        Document doc= Jsoup.connect(url).get();//如http://baike.baidu.com/view/2216.htm（唐太宗李世民）
//        String s = doc.baseUri();
        String s = doc.location();
        System.out.println(s);
    }


}
