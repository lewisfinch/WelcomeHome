package com.wh.service;

import com.wh.dto.CategoryDTO;
import com.wh.dto.DateDTO;
import com.wh.dto.OrderDTO;
import com.wh.dto.RelevantOrderDTO;
import com.wh.pojo.*;

import java.util.List;

public interface OrderService {

    boolean checkUserName(String userName);

    int newOrder();

    List<Item> getCategory(Category category);

    boolean addToOrder(OrderDTO orderDTO);

    List<RelevantOrderDTO> getRelevantOrders(Person person);

    List<CategoryDTO> getPopularCategories(DateDTO dateDTO);
    List<Category> getExistingCategory();
}
