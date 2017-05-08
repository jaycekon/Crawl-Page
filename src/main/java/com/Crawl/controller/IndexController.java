package com.Crawl.controller;

import com.Crawl.service.CrawlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "index")
    public String index(Model model) {
        List<String> list = CrawlService.crawl();
        model.addAttribute("list", list);
        return "index";
    }
}
