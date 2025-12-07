package com.app.protoSmart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
    @GetMapping("/")
    @ResponseBody
    public String temp() {
        return "Welcome! Your controller is working.";
    }
}
