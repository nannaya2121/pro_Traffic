package org.go.traffic.controller;

import java.io.IOException;

import org.go.traffic.api.TrafficAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        System.out.println("index Access Success");
        try {
        	TrafficAPI.apiCall();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "index";
    }
}
