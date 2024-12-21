package com.Ecommerce.web.application.Controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yash")
public class Controller {

    @GetMapping
    public String get(){
        return "hello";
    }
}
