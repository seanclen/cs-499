package com.seanclen.capstone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CapstoneController {
    @GetMapping("/")
    public String dashboard(Model model) {
        return "dashboard";
    }
}
