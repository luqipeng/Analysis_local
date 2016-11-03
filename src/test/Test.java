package test;

import com.buss.caiji.method.GroupMethod;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.xmlbeans.impl.common.XPath;

import javax.swing.text.Document;
import javax.xml.ws.spi.http.HttpContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LuQP on 2016/8/11.
 */
public class Test {
    public static void main(String[] args)throws Exception{
        System.out.println("test");
        doCatch("", "", "http://www.aliexpress.com/item/**-**/997470494.html");
    }

    private static void doCatch(String name, String img, String url) throws InterruptedException, IOException {
        URL serverUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) serverUrl
                .openConnection();
        conn.setRequestProperty("Cookie","ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
        conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        conn.setRequestMethod("GET");
        // 必须设置false，否则会自动redirect到Location的地址
        conn.setInstanceFollowRedirects(false);

        conn.addRequestProperty("Accept-Charset", "UTF-8;");
        conn.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
        //conn.addRequestProperty("Referer", "http://zuidaima.com/");
        conn.connect();
        String location = conn.getHeaderField("Location");
        System.out.println(location);
        serverUrl = new URL("http:"+location);
        conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestProperty("Cookie","ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
        conn.setRequestMethod("GET");
        BufferedReader br = null;
        try {
            //conn.addRequestProperty("X-Forwarded-For", ip);
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
        } catch (Exception e) {
            conn.disconnect();
            System.out.println("请求失败");
            return;
        }
        String strRead = "";
        boolean start = false;
        String imgRegular= "src=\"([^\"]*)\"";
        String nameRegular= "title=\"([^\"]*)\"";
        GroupMethod gMethod = new GroupMethod();
        boolean go = true;
        while ((strRead = br.readLine()) != null && go) {
            if(strRead.contains("class=\"ui-image-viewer-thumb-frame")){
                start = true;
            }
            if(start){
                System.out.println(strRead);
                if(strRead.contains("<img")){
                    img = gMethod.regularGroup(imgRegular, strRead);
                    name =  gMethod.regularGroup(nameRegular, strRead);

                    System.out.println(img);
                    System.out.println(name);
                    go = false;
                    return;
                }

            }
        }
    }

    /*public String test1(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        CustomRedirectHandler handler=new CustomRedirectHandler();
        httpClient.setRedirectHandler(handler);
        HttpGet httpget = new HttpGet(url);
        HttpContext context = new BasicHttpContext();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget, context);
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
            try {
                throw new IOException(response.getStatusLine().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(XPath.ExecutionContext.HTTP_REQUEST);
        HttpHost currentHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
        String currentUrl = (currentReq.getURI().isAbsolute()) ? currentReq.getURI().toString(): (currentHost.toURI() + currentReq.getURI());
        return currentUrl;
    }*/
}
