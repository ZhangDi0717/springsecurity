package zhangdi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/admin")
    private String admin(){
        return "hello admin";
    }

}
