package com.seanclen.capstone;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class CapstoneController {
    @GetMapping("/")
    ResponseEntity<String> Message() {
        return new ResponseEntity<>("CS-499 Capstone", OK);
    }
}
