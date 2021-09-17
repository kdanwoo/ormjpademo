package com.kdanwoo.ormjpademo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home"; // home.html 타임리프 파일을 찾아감
    }
}
