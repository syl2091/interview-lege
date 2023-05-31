package com.lege.cdl.controller;

import com.lege.cdl.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @GetMapping("/search")
    public List<Map> search(String keyword){
        return articleSearchService.search(keyword);
    }
}
