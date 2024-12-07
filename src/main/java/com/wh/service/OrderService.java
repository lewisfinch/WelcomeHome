package com.wh.service;

import com.wh.dto.OrderDTO;
import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
import com.wh.pojo.Category;
import com.wh.pojo.Item;
import com.wh.pojo.ItemIn;
import com.wh.pojo.Ordered;

import java.util.List;

public interface OrderService {

    boolean checkUserName(String userName);

    int newOrder();

    List<Item> getCategory(Category category);

    boolean addToOrder(OrderDTO orderDTO);
}
