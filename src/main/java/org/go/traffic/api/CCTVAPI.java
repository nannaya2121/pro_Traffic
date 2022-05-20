package org.go.traffic.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CCTVAPI {
	
	public static JSONObject cctvCall() throws IOException{
		  StringBuilder urlBuilder = new StringBuilder("https://openapi.its.go.kr:9443/cctvInfo"); /*URL*/
		  urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + URLEncoder.encode("44f4eef6797d479f9a50e151ff333872", "UTF-8")); /*공개키*/
		  urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("ex", "UTF-8")); /*도로유형*/
		  urlBuilder.append("&" + URLEncoder.encode("cctvType","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*CCTV유형*/
		  urlBuilder.append("&" + URLEncoder.encode("minX","UTF-8") + "=" + URLEncoder.encode("127.016256", "UTF-8")); /*최소경도영역*/
		  urlBuilder.append("&" + URLEncoder.encode("maxX","UTF-8") + "=" + URLEncoder.encode("129.093443", "UTF-8")); /*최대경도영역*/
		  urlBuilder.append("&" + URLEncoder.encode("minY","UTF-8") + "=" + URLEncoder.encode("35.242427", "UTF-8")); /*최소위도영역*/
		  urlBuilder.append("&" + URLEncoder.encode("maxY","UTF-8") + "=" + URLEncoder.encode("37.522610", "UTF-8")); /*최대위도영역*/
		  urlBuilder.append("&" + URLEncoder.encode("getType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*출력타입*/
		  URL url = new URL(urlBuilder.toString());
		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		  conn.setRequestMethod("GET");
		  conn.setRequestProperty("Content-type", "text/xml;charset=UTF-8");
		  log.debug("CCTV API Response code: " + conn.getResponseCode());
		  BufferedReader rd;
		  if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		   rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		  } else {
		   rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		  }
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		   sb.append(line);
		  }
		  rd.close();
		  conn.disconnect();
		  log.debug("cctv api 호출 : " + sb.toString());
		  
		  String jsonStr=sb.toString();//sb는 StringBuilder 객체. API의response를 통해 값을 받은 상태. 
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(jsonStr);
				JSONObject jsonObj = (JSONObject) obj;
				return jsonObj;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
	}

}
