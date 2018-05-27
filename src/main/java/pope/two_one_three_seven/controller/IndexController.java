package pope.two_one_three_seven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String foo(){
        return "index";
    }

}
