package com.sist.manager;
/*
 *  json 파싱
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonParser;
import com.sist.vo.*;
import com.sist.vo.NewsVO;

public class NewsManager {

	public static void main(String[] args) {
		newsFindData("축구");
	}
	  public static List<NewsVO> newsFindData(String fd) {
		  List<NewsVO> list =  new ArrayList<NewsVO>();
		  
		  
	        String clientId = "@@"; //네이버 클라이언트 아이디
	        String clientSecret = "@@"; //네이버 클라이언트 비번


	        String text = null;
	        try {
	            text = URLEncoder.encode(fd, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	        }


	        String apiURL = "https://openapi.naver.com/v1/search/news.json?display=100&query=" + text;    // JSON 결과
	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과


	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);


	        System.out.println(responseBody);
	        
	        try
	        {
	        	// {} 이렇게 시작하는 json은 jsonObject / [] => JSONArray로 받아야 한다
	           JSONParser jp = new JSONParser();
	           
	           // {items:[]}
	           JSONObject root = (JSONObject)jp.parse(responseBody);
			   /* System.out.println(root.toJSONString());  출력이 제대로 되는지 확인차*/
	        
	           JSONArray arr = (JSONArray)root.get("items");
			   /* System.out.println(arr.toJSONString()); */
	        
	           for(int i=0;i<arr.size();i++)
	           {
	              	JSONObject obj = (JSONObject)arr.get(i);
					/* System.out.println(obj.toJSONString()); */
					   
	              	String title = (String)obj.get("title");
	              	String desc =(String)obj.get("description");
	              	String link =(String)obj.get("link");
	              	
	              	// 데이터 분리시킴
	              	System.out.println(i+1);
	              	System.out.println(title);
	              	System.out.println(desc);
	              	System.out.println(link);
	              	System.out.println("======================================================================================================================================");
	              	
	              	NewsVO vo = new NewsVO();
	              	vo.setTitle(title);
	              	vo.setDesc(desc);
	              	vo.setLink(link);
	              	
	              	list.add(vo);
	           }
	        
	        }catch (Exception ex) {}
	        
	        return list;
	    }


	    private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }


	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 오류 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }


	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }


	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);


	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();


	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }


	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
	        }
	    }
}
