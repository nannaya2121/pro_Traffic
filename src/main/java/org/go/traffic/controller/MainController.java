package org.go.traffic.controller;

import java.io.IOException;

import org.go.traffic.api.CCTVAPI;
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
			System.out.println("CCTV Result : " + result); 
			
			System.out.println("ttttttt >> : " + result.get("response.coordtype"));
			
			
			//model.addAttribute("apiResult",result);
			
			return "index";
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "index";
    }
}
