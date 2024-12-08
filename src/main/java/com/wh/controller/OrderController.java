package com.wh.controller;

import com.wh.dto.OrderDTO;
import com.wh.dto.RelevantOrderDTO;
import com.wh.pojo.*;
import com.wh.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkUserName")
    public Result checkUserName(@RequestBody Person person) {
        String userName = person.getUserName();
        boolean result = orderService.checkUserName(userName);
        if (result) {
            return Result.success("Valid userName");
        } else {
            return Result.error("Invalid userName");
        }
    }

    @GetMapping("/newOrder")
    public Result newOrder() {
        int orderID = orderService.newOrder();
        if(orderID > 0) {
            return Result.success(orderID);
        } else {
            return Result.error("Failed to create new order");
        }
    }

    @GetMapping("/getCategory")
    public Result getCategory(@RequestBody Category category) {
        List<Item> itemList = orderService.getCategory(category);
        if (itemList.isEmpty()) {
            return Result.error("No items found for category: " + category.getMainCategory() + ", " + category.getSubCategory());
        }
        return Result.success(itemList);
    }

    @PostMapping("/addToOrder")
    public Result addToOrder(@RequestBody OrderDTO orderDTO) {
        log.info(orderDTO.toString());
        boolean result = orderService.addToOrder(orderDTO);
        if(result) {
            return Result.success("Successfully added to the order");
        } else {
            return Result.error("Failed to add to the order");
        }
    }

    @GetMapping("/userTasks")
    public Result userTasks(@RequestBody Person person) {
        List<RelevantOrderDTO> orders = orderService.getRelevantOrders(person);
        if(orders.isEmpty()) {
            return Result.error("No relevant orders found");
        }
        return Result.success(orders);
    }

}
