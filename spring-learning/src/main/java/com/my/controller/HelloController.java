package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@ResponseBody
//@RestController
public class HelloController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "hello world!");
        return "index";
    }
    @ResponseBody
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world!";
    }

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "goto 成功");
        request.setAttribute("code", 200);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(@RequestAttribute("msg") String msg,
                                       @RequestAttribute("code") Integer code,
                                       HttpServletRequest request) {
        Object requestMsg = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        map.put("request_msg", requestMsg);
        map.put("annotation_msg", msg);
        map.put("code", code);
        return map;
    }

    // http://localhost:8080/matrix/sell;low=100;brand=a,b,c
    @ResponseBody
    @GetMapping("/matrix/{path}")
    public Map<String, Object> matrix(@MatrixVariable("low") String low,
                                       @MatrixVariable("brand") List<String> brand,
                                       @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // http://localhost:8080/boss/1;age=29/2;age=24
    @ResponseBody
    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "bossId") String bossAge,
                                      @MatrixVariable(value = "age", pathVar = "empId") String empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }
}
