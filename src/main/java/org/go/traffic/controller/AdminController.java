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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired(required = true)
    private CityService cityService;

    @Autowired
    private GugunService gugunService;

    @GetMapping("/admin")
    public String admin(Model model) {
        System.out.println("admin Access Success");

        List<CityDTO> list = cityService.cityFindAll();
        model.addAttribute("cityList", list);
        System.out.println(list.toString());
        return "admin/admin";
    }

    @PostMapping("/gugunInsert")
    public String gugunInsert(GugunDTO dto, RedirectAttributes rttr) {
        System.out.println("dto : " + dto.toString());
        gugunService.gugunInsert(dto);
        rttr.addFlashAttribute("msg", "Success");
        return "redirect:admin/admin";
    }

}
