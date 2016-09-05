package de.scout24.financing.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ajitchahal on 17/08/15.
 */
@Controller
public class RouteController {

    @ModelAttribute("request")
    public HttpServletRequest getRequest(HttpServletRequest request) {
        return request;
    }
    
    @RequestMapping("/admin")
    public String admin() {
        return "/admin/index";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/resources")
    public String resources() {
        return "/partial/resources";
    }

    @RequestMapping("/view")
    public String viewResolver(@RequestParam("viewName") String viewName) {
        return viewName;
    }

//    @RequestMapping("/error")
//    public String errorViewer() {
//        return "error";
//    }
}
