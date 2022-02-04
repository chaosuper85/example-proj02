package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/4 11:21 上午
 */
@RestController
public class EsController {
    //@Autowired
    //private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/addData")
    public String addData() {
        /*
        Item item = Item.builder()
                .id(1000L)
                .brand("brand")
                .images("images_url")
                .title("title")
                .price(0.0d)
                .build();

        IndexQuery indexQuery = new IndexQueryBuilder().withObject(item).build();
        elasticsearchTemplate.index(indexQuery);

         */
        return  "ok";
    }
}
