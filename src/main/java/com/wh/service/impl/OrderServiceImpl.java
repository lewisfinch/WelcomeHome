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
    public boolean addToOrder(OrderDTO orderDTO) {
        Ordered ordered = new Ordered();
        ItemIn itemIn = new ItemIn();
        Date current = Date.valueOf(LocalDate.now());

        ordered.setOrderID(Integer.parseInt(orderDTO.getOrderID()));
        ordered.setOrderDate(current);
        ordered.setOrderNotes(orderDTO.getOrderNotes());
        ordered.setClient(orderDTO.getClient());
        ordered.setSupervisor(orderDTO.getSupervisor());

        itemIn.setOrderID(Integer.parseInt(orderDTO.getOrderID()));
        itemIn.setItemID(Integer.parseInt(orderDTO.getItemID()));
        itemIn.setFound(true);

        try {
            boolean orderAdded = orderMapper.addToOrder(ordered);
            if (!orderAdded) {
                return false;
            }

            boolean itemAdded = orderMapper.addToItemIn(itemIn);
            if (!itemAdded) {
                return false;
            }

            return true;
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
    public int newOrder() {
        return orderMapper.newOrder() + 1;
    }
}
