package zhangdi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员服务
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/homepage")
    @ResponseBody
    private String admin(){
        return "hello admin";
    }

}
