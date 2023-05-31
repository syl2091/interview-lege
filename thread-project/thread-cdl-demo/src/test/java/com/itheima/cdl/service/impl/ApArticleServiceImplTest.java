package com.lege.cdl.service.impl;

import com.lege.cdl.CDLApplication;
import com.lege.cdl.service.ApArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = CDLApplication.class)
@RunWith(SpringRunner.class)
public class ApArticleServiceImplTest {

    @Autowired
    private ApArticleService apArticleService;

    @Test
    public void importAll() {
        apArticleService.importAll();
    }
}