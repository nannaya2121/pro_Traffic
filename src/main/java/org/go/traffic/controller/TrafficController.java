package org.go.traffic.controller;

import java.io.IOException;
import java.util.List;

import org.go.traffic.api.TrafficAPI;
import org.go.traffic.model.CityDTO;
import org.go.traffic.model.GugunDTO;
import org.go.traffic.service.CityService;
import org.go.traffic.service.GugunService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/traffic/*")
public class TrafficController {
	
	@Autowired
    private CityService cityService;
	
	@Autowired
	private GugunService gugunService;
	
	@GetMapping("/searchTraffic")
	public String test(Model model) {
		List<CityDTO> cityList = cityService.cityFindAll();
		model.addAttribute("cityList", cityList);
		
		List<GugunDTO> gugunList = gugunService.findAllGugun();
		model.addAttribute("gugunList", gugunList);
		
		return "traffic/searchTraffic";
	}

    
    @PostMapping("/gugunSearchList")
    @ResponseBody
    public List<GugunDTO> gugunSearchList(@RequestParam("city_value") String city_value, Model model){
    	
    	List<GugunDTO> gugunOneList = gugunService.gugunOneList(city_value);
    	System.out.println(gugunOneList.toString());
    	model.addAttribute("gugunOneList",gugunOneList);
    	return gugunOneList;
    }
    
    
    @PostMapping("/moveData")
    @ResponseBody
    public JSONObject moveData(@RequestParam("searchYear") String searchYear,
    		@RequestParam("city_value") String city_value,
    		@RequestParam("gugun_value") String gugun_value){
    	System.out.println("타니?");
    	StringBuilder result = null;
    	JSONObject jsonObj = null;
    	try {
    		result = TrafficAPI.apiCall(searchYear, city_value, gugun_value);
    		System.out.println("result 값 확인 : " + result);
    		
    		
    		String jsonStr=result.toString();//sb는 StringBuilder 객체. API의response를 통해 값을 받은 상태. 
    		JSONParser parser = new JSONParser();
    		Object obj = parser.parse(jsonStr);
    		jsonObj = (JSONObject) obj;
    		
    		return jsonObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return jsonObj;
    }
}
