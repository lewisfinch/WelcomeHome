package com.wh.controller;

import com.wh.dto.CategoryDTO;
import com.wh.dto.DateDTO;
import com.wh.dto.OrderDTO;
import com.wh.dto.RelevantOrderDTO;
import com.wh.pojo.*;
import com.wh.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.sql.Date;

@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkUserName")
    public Result checkUserName(@RequestParam String userName) {

        boolean result = orderService.checkUserName(userName);
        if (result) {
            return Result.success("Valid userName");
        } else {
            return Result.error("Invalid userName");
        }
    }

    @GetMapping("/newOrder")
    public Result newOrder(@RequestParam String client, String supervisor) {
        Ordered ordered=new Ordered();
        ordered.setSupervisor(supervisor);
        ordered.setClient(client);
        int orderID = orderService.newOrder(ordered);
        if (orderID > 0) {
            return Result.success(orderID);
        } else {
            return Result.error("Failed to create new order");
        }
    }

    @GetMapping("/getCategory")
    public Result getCategory(@RequestParam String mainCategory, String subCategory) {
        Category category=new Category(mainCategory,subCategory,"NOnOtes");
        List<Item> itemList = orderService.getCategory(category);
        if (itemList.isEmpty()) {
            return Result.error("No items found for category: " + category.getMainCategory() + ", " + category.getSubCategory());
        }
        return Result.success(itemList);
    }
    @GetMapping("/getExistingCategory")
    public Result getExistingCategory() {
        List<Category> results = orderService.getExistingCategory();
        if(results.isEmpty()) {
            return Result.error("No categories found");
        } else {
            return Result.success(results);
        }
    }

    @PostMapping("/addToOrder")
    public Result addToOrder(@RequestBody OrderDTO orderDTO) {
        log.info(orderDTO.toString());
        boolean result = orderService.addToOrder(orderDTO);
        if (result) {
            return Result.success("Successfully added to the order");
        } else {
            return Result.error("Failed to add to the order");
        }
    }

    @GetMapping("/userTasks")
    public Result userTasks(@RequestParam String userName) {
        Person person = new Person();
        person.setUserName(userName);
        List<RelevantOrderDTO> orders = orderService.getRelevantOrders(person);
        if (orders.isEmpty()) {
            return Result.error("No relevant orders found");
        }
        return Result.success(orders);
    }


    @GetMapping("/popularCategories")
    public Result popularCategories(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            // 将字符串转换为 java.sql.Date
            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);

            // 构造 DateDTO 对象
            DateDTO dateDTO = new DateDTO();
            dateDTO.setStartDate(start);
            dateDTO.setEndDate(end);

            // 调用服务层方法
            List<CategoryDTO> results = orderService.getPopularCategories(dateDTO);

            if (results.isEmpty()) {
                return Result.error("No popular categories found");
            } else {
                return Result.success(results);
            }
        } catch (IllegalArgumentException e) {
            return Result.error("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}