package org.go.traffic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/traffic/*")
public class TrafficController {

    @GetMapping("/goTraffic")
    public String goTraffic() {
        System.out.println("goTraffic Access Success");
        return "traffic/goTraffic";
    }

}
