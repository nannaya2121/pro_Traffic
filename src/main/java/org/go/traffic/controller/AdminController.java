package org.go.traffic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // @Autowired
    // private CityService cityService;
    //
    // @Autowired
    // private GugunService gugunService;
    //
    // @GetMapping("/admin")
    // public String admin(Model model) {
    // System.out.println("admin Access Success");
    //
    // List<CityDTO> list = cityService.cityFindAll();
    // model.addAttribute("cityList", list);
    // return "admin/admin";
    // }
    //
    // @PostMapping("/gugunInsert")
    // public String gugunInsert(GugunDTO dto, RedirectAttributes rttr) {
    // System.out.println("dto : " + dto.toString());
    // gugunService.gugunInsert(dto);
    // rttr.addFlashAttribute("msg", "Success");
    // return "redirect:/admin/admin";
    // }

}
