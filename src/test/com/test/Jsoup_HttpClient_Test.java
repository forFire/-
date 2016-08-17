package cn.shb.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 解析http://cpc.people.com.cn/xuexi/GB/387488/index.html
 * 中的标题、日期、来源、内容
 * @author Administrator
 * 
 */
public class Jsoup_HttpClient_Test {
    public static void main(String[] args) throws Exception {
        //第一步：根据url使用httpclient获取页面信息，方法getHtmlByUrl();
        String html = getHtmlByUrl("http://cpc.people.com.cn/xuexi/GB/387488/index.html");
        if (html!=null&&!"".equals(html)) {
            //第二步：使用jsoup解析html，获取内容
            Document doc = Jsoup.parse(html);
            Elements linksElements = doc.select("ul[id=tiles]>li>div[class=con]");
             
            for (Element ele:linksElements) {
                String title = ele.select(">h3>a").text();
                String source = ele.select(">em>a").text();
                ele.select(">em>a").empty();
                String date = ele.select(">em").text().replace("来源：", "").replace("（", "").replace("）", "");
                String text = ele.select(">span>a").text();

                System.out.println(title);
                System.out.println(source);
                System.out.println(date);
                System.out.println(text);
                System.out.println();
                System.out.println();
            }
        }
    }
    /**
     * 根据URL获得所有的html信息
     * @param url
     * @return
     */
    public static String getHtmlByUrl(String url){
        String html = null;
        //创建httpClient对象
        HttpClient httpClient = new DefaultHttpClient();
        //以get方式请求该URL
        HttpGet httpget = new HttpGet(url);
        try {
            //得到responce对象
            HttpResponse responce = httpClient.execute(httpget);
            //返回码
            int resStatu = responce.getStatusLine().getStatusCode();
            if (resStatu==HttpStatus.SC_OK) {//200正常  其他就不对
                //获得输入流
                InputStream entity = responce.getEntity().getContent();
                if (entity!=null) {
                    //通过输入流转为字符串获得html源代码  注：可以获得实体，然后通过 EntityUtils.toString方法获得html
                	//但是有可能出现乱码，因此在这里采用了这种方式
                    html=getStreamString(entity);
                     System.out.println(html);
                }
            }
        } catch (Exception e) {
            System.out.println("访问【"+url+"】出现异常!");
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return html;
    }

    /**
    * 将一个输入流转化为字符串
    */
    public static String getStreamString(InputStream tInputStream){
        if (tInputStream != null){
        try{
	        BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
	        StringBuffer tStringBuffer = new StringBuffer();
	        String sTempOneLine = new String("");
        while ((sTempOneLine = tBufferedReader.readLine()) != null){
                tStringBuffer.append(sTempOneLine+"\n");
        }
            return tStringBuffer.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
       }
         return null;
    }
}