package com.wh.controller;

import com.wh.pojo.Item;
import com.wh.pojo.Result;
import com.wh.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/items")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public Result list(){
        log.info("Search all items");
        List<Item> itemList =  itemService.list();
        return Result.success(itemList);
    }


}
