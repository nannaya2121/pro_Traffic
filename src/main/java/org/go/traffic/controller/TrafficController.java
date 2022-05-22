package org.go.traffic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.go.traffic.api.CCTVAPI;
import org.go.traffic.api.TrafficAPI;
import org.go.traffic.model.AccidentDTO;
import org.go.traffic.model.CityDTO;
import org.go.traffic.model.GugunDTO;
import org.go.traffic.service.AccidentService;
import org.go.traffic.service.CityService;
import org.go.traffic.service.GugunService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/traffic/*")
@Log4j2
public class TrafficController {
	
	@Autowired
    private CityService cityService;
	
	@Autowired
	private GugunService gugunService;
	
	@Autowired
	private AccidentService accidentService;
	
	@GetMapping("/cctv")
	public String cctvPage() {
		
		return "traffic/cctv";
	}
	
	@PostMapping("/cctvInfo")
	@ResponseBody
	public List<Map<String, Object>> cctvInfo(@RequestParam("lineInfo") String lineInfo){
		
		System.out.println("lineInfo : " + lineInfo);
		
		List<Map<String, Object>> arrangeList = new ArrayList<>();
		try {
			JSONObject result = CCTVAPI.cctvCall();
			JSONObject responseObj = (JSONObject) result.get("response");
			JSONArray dataArray = (JSONArray) responseObj.get("data");
			
			for(int i = 0; i < dataArray.size(); i++) {
				JSONObject dataObj = (JSONObject) dataArray.get(i);
				String checkExLine = (String) dataObj.get("cctvname");

				if(lineInfo.equals("kyeongbooLine")) {
					
					if(checkExLine.contains("[경부선]")) {
						Map<String, Object> arrangeMap = new HashMap<String, Object>();
						String exLine = (String) dataObj.get("cctvname");
						String cctvURL = (String) dataObj.get("cctvurl");
						double latitude = (double) dataObj.get("coordy");
						double longitude = (double) dataObj.get("coordx");
						
						arrangeMap.put("exLine", exLine);
						arrangeMap.put("cctvURL", cctvURL);
						arrangeMap.put("latitude", latitude);
						arrangeMap.put("longitude", longitude);
						
						arrangeList.add(arrangeMap);
					}
					
				}else if(lineInfo.equals("joongbooLine")) {
					
					if(checkExLine.contains("[중부선]")) {
						Map<String, Object> arrangeMap = new HashMap<String, Object>();
						String exLine = (String) dataObj.get("cctvname");
						String cctvURL = (String) dataObj.get("cctvurl");
						double latitude = (double) dataObj.get("coordy");
						double longitude = (double) dataObj.get("coordx");
						
						arrangeMap.put("exLine", exLine);
						arrangeMap.put("cctvURL", cctvURL);
						arrangeMap.put("latitude", latitude);
						arrangeMap.put("longitude", longitude);
						
						arrangeList.add(arrangeMap);
					}
					
				}else if(lineInfo.equals("honamLine")) {
					
					if(checkExLine.contains("[호남선]")) {
						Map<String, Object> arrangeMap = new HashMap<String, Object>();
						String exLine = (String) dataObj.get("cctvname");
						String cctvURL = (String) dataObj.get("cctvurl");
						double latitude = (double) dataObj.get("coordy");
						double longitude = (double) dataObj.get("coordx");
						
						arrangeMap.put("exLine", exLine);
						arrangeMap.put("cctvURL", cctvURL);
						arrangeMap.put("latitude", latitude);
						arrangeMap.put("longitude", longitude);
						
						arrangeList.add(arrangeMap);
					}
					
				}
				
				
			} 
			
			log.info("list 개수 : " + arrangeList.size());

			return arrangeList;
			
		} catch (Exception e) {
			log.error(e);
		}
		return arrangeList;
		
	}
	
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
    	log.info("gugunOneList : "+gugunOneList.toString());
    	model.addAttribute("gugunOneList",gugunOneList);
    	return gugunOneList;
    }
    
    
    @PostMapping("/moveData")
    @ResponseBody
    public JSONObject moveData(@RequestParam("searchYear") String searchYear,
    		@RequestParam("city_value") String city_value,
    		@RequestParam("gugun_value") String gugun_value){
    	
    	try {
    		JSONObject result = TrafficAPI.apiCall(searchYear, city_value, gugun_value);
    		log.info("moveData 값 확인 : " + result);
    		return result;
		} catch (Exception e) {
			log.error(e);
		}
    	return null;
    }
    
    @PostMapping("/accidentDetail")
    public String accidentDetailPage(@RequestParam("detailSidoValue") String detailSidoValue,
    		@RequestParam("detailGugunValue") String detailGugunValue,
    		@RequestParam("totalVal") String totalVal,
    		Model model){
    	CityDTO cityName = cityService.cityFindByValue(detailSidoValue);
    	GugunDTO gugunName = gugunService.gugunFindByValue(detailGugunValue);
    	
    	log.info("totalVal 값 확인 : " + totalVal);
    	
    	AccidentDTO dto = accidentService.tempSave(totalVal);
    	log.info("DTO 값 확인 : " + dto.toString());
    	log.info("cityName 값 : " + cityName);
    	log.info("gugunName 값 : " + gugunName);
    	
    	Map<String, Object> names = new HashMap<>();
    	names.put("cityName", cityName.getCity_name());
    	names.put("gugunName", gugunName.getGugun_name());
    	
    	model.addAttribute("dto", dto);
    	model.addAttribute("sidoGugunName", names);
    	
    	return "traffic/accidentDetail";
    }
    
}
