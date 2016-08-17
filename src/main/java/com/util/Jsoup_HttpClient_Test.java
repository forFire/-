package com.util;

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
 * ����http://cpc.people.com.cn/xuexi/GB/387488/index.html
 * �еı��⡢���ڡ���Դ������
 * @author Administrator
 * 
 */
public class Jsoup_HttpClient_Test {
    public static void main(String[] args) throws Exception {
        //��һ�������urlʹ��httpclient��ȡҳ����Ϣ������getHtmlByUrl();
        String html = getHtmlByUrl("http://cpc.people.com.cn/xuexi/GB/387488/index.html");
        if (html!=null&&!"".equals(html)) {
            //�ڶ�����ʹ��jsoup����html����ȡ����
            Document doc = Jsoup.parse(html);
            Elements linksElements = doc.select("ul[id=tiles]>li>div[class=con]");
             
            for (Element ele:linksElements) {
                String title = ele.select(">h3>a").text();
                String source = ele.select(">em>a").text();
                ele.select(">em>a").empty();
                String date = ele.select(">em").text().replace("��Դ��", "").replace("��", "").replace("��", "");
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
     * ���URL������е�html��Ϣ
     * @param url
     * @return
     */
    public static String getHtmlByUrl(String url){
        String html = null;
        //����httpClient����
        HttpClient httpClient = new DefaultHttpClient();
        //��get��ʽ�����URL
        HttpGet httpget = new HttpGet(url);
        try {
            //�õ�responce����
            HttpResponse responce = httpClient.execute(httpget);
            //������
            int resStatu = responce.getStatusLine().getStatusCode();
            if (resStatu==HttpStatus.SC_OK) {//200��  ����Ͳ���
                //���������
                InputStream entity = responce.getEntity().getContent();
                if (entity!=null) {
                    //ͨ��������תΪ�ַ���htmlԴ����  ע�����Ի��ʵ�壬Ȼ��ͨ�� EntityUtils.toString�������html
                	//�����п��ܳ������룬�����������������ַ�ʽ
                    html=getStreamString(entity);
                     System.out.println(html);
                }
            }
        } catch (Exception e) {
            System.out.println("���ʡ�"+url+"�������쳣!");
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return html;
    }

    /**
    * ��һ��������ת��Ϊ�ַ�
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