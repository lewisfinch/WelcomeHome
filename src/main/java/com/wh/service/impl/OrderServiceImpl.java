package com.wh.service.impl;

import com.wh.dto.CategoryDTO;
import com.wh.dto.DateDTO;
import com.wh.dto.OrderDTO;
import com.wh.dto.RelevantOrderDTO;
import com.wh.mapper.OrderMapper;
import com.wh.pojo.*;
import com.wh.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Override
    public List<RelevantOrderDTO> getRelevantOrders(Person person) {
        return orderMapper.getRelevantOrders(person);
    }
    @Override
    public List<Category> getExistingCategory() {
        return orderMapper.getExistingCategory();
    }
    @Override
    public List<CategoryDTO> getPopularCategories(DateDTO dateDTO) {
        return orderMapper.getPopularCategories(dateDTO);
    }

    @Override
    public Ordered getCurrentOrder(String orderID) {
        return orderMapper.getCurrentOrder(orderID);
    }

    @Override
    public boolean addToOrder(OrderDTO orderDTO) {
        ItemIn itemIn = new ItemIn();

        itemIn.setOrderID(Integer.parseInt(orderDTO.getOrderID()));
        itemIn.setItemID(Integer.parseInt(orderDTO.getItemID()));
        itemIn.setFound(true);

        try {
            return orderMapper.addToItemIn(itemIn);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Item> getCategory(Category category) {
        return orderMapper.getCategory(category);
    }

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public boolean checkUserName(String userName) {
        int count = orderMapper.checkUserName(userName);
        return count > 0;
    }

    @Override
    public int newOrder(Ordered ordered) {
        int orderID = orderMapper.newOrder() + 1;
        Ordered newOrder = new Ordered();
        newOrder.setOrderID(orderID);
        newOrder.setOrderDate(Date.valueOf(LocalDate.now()));
        newOrder.setSupervisor(ordered.getSupervisor());
        newOrder.setClient(ordered.getClient());
        orderMapper.addToOrder(newOrder);
        return orderID;
    }
}
