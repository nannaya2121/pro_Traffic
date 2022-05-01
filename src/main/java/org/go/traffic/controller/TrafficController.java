package org.go.traffic.controller;

import java.util.List;

import org.go.traffic.model.CityDTO;
import org.go.traffic.model.GugunDTO;
import org.go.traffic.service.CityService;
import org.go.traffic.service.GugunService;
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
    public List<GugunDTO> gugunSearchList(@RequestParam("city_value") String city_value){
    	
    	List<GugunDTO> gugunOneList = gugunService.gugunOneList(city_value);
    	System.out.println(gugunOneList.toString());
    	return gugunOneList;
    }

}
