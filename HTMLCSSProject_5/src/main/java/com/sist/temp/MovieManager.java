package com.sist.temp;
import java.util.*;
import java.net.*;
import java.io.*;
public class MovieManager {
  private String[] urls = {
	"",
	"searchMainDailyBoxOffice.do",
	"searchMainRealTicket.do",
	"searchMainDailySeatTicket.do"
	
  };
  private String baseURL="https://www.kobis.or.kr/kobis/business/main/";
  public String movieListeData(int no) // no의 값은 url들을 가리킴
  {
	  String data="";
	  try
	  {
		URI uri = new URI(baseURL+urls[no]);
		URL url = uri.toURL();
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		if(conn!=null) // 사이트 연결 완료
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while(true)
			{
				String s = br.readLine();
				if(s==null)
					break;
				data+=s; // 문자열 결합을 시켜야 데이터가 받아진다
			}
		}
				
				
	  } 
	  catch (Exception ex) {}
	  return data;
  }
  public static void main(String[] args) 
  { // main + ctrl + space 하면 자동으로 메인 생성됨
	  
	MovieManager m = new MovieManager();
	String s = m.movieListeData(1);
	System.out.println(s);
	
}
}
