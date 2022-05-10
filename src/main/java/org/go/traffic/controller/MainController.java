package org.go.traffic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.go.traffic.api.CCTVAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(Model model) {
		System.out.println("index Access Success");
		try {

			JSONObject result = CCTVAPI.cctvCall();
			//System.out.println("ttt >>>> "+result.toString());
			
			JSONObject test1 = (JSONObject) result.get("response");
			
			JSONArray test2 = (JSONArray) test1.get("data");
			System.out.println("=====================================");
			System.out.println("============="+test2.size()+"==============");
			System.out.println("=====================================");
			
			Map<String, Object> cctvname = new HashMap<String, Object>();
			Map<String, Object> cctvurl = new HashMap<String, Object>();
			
			List<Map<String, Object>> cctvnameList = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> cctvurlList = new ArrayList<Map<String,Object>>();
			
			for(int i = 0; i < 1; i++) {
				JSONObject test3 = (JSONObject) test2.get(i);
				System.out.println("형식 : " + test3.get("cctvformat"));
				cctvname.put("cctvname", test3.get("cctvname"));
				cctvnameList.add(cctvname);
				
				cctvurl.put("cctvurl", test3.get("cctvurl"));
				cctvurlList.add(cctvurl);
				
//				System.out.println(test3.get("cctvname"));
//				System.out.println(test3.get("cctvurl"));
			}
			System.out.println("cctvnameList in Map 사이즈 확인 : " + cctvnameList.size());
			System.out.println("cctvnameList in Map 값 확인 : " + cctvnameList.toString());
			System.out.println();
			System.out.println();
			System.out.println("cctvurlList in Map 사이즈 확인 : " + cctvurlList.size());
			System.out.println("cctvurlList in Map 값 확인 : " + cctvurlList);
			
			model.addAttribute("cctvurlList",cctvurlList);
			
			//테스트용
			model.addAttribute("cctvurl",cctvurl);
			
			
			
//			JSONParser parse = new JSONParser();
//			obj = parse.parse(result.toString());
//			
//			JSONObject jsonObj = (JSONObject) obj;
//			
//			String aaa = (String) jsonObj.get("coordtype");
//			System.out.println("11>> " + aaa);
//			JSONObject test = new JSONObject(result);
//			
//			JSONObject test1 = test.getJSONObject("data");
//			System.out.println("test1 값 확인 : " + test1.toString());

			// model.addAttribute("apiResult",result);

			return "index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

}
