package com.wh.controller;

import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
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

    @GetMapping("/list")
    public Result list(){
        log.info("Search all items");
        List<Item> itemList =  itemService.list();
        return Result.success(itemList);
    }

    @GetMapping("/find/{itemID}")
    public Result findItemLocations(@PathVariable("itemID") String itemID) {
        log.info("Finding locations for item: {}", itemID);
        List<PieceLocationDTO> locations = itemService.findItemLocations(itemID);
        if (locations.isEmpty()) {
            return Result.error("No locations found for item ID: " + itemID);
        }
        return Result.success(locations);
    }

    @GetMapping("/order/{orderID}")
    public Result findOrderItems(@PathVariable("orderID") String orderID) {
        log.info("Finding items for order: {}", orderID);
        List<OrderItemLocationDTO> orderItems = itemService.findOrderItems(orderID);
        if (orderItems.isEmpty()) {
            return Result.error("No items found for order ID: " + orderID);
        }
        return Result.success(orderItems);
    }



}
