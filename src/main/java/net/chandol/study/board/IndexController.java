package net.chandol.study.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String redirectToArticles(){
        return "redirect:/articles";
    }
}
