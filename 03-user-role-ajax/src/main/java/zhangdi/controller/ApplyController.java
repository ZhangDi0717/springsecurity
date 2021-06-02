package zhangdi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/apply")
public class ApplyController {
    @RequestMapping("homepage")
    @ResponseBody
    private String hemoPage(){

        return "hello apply!";
    }
}
