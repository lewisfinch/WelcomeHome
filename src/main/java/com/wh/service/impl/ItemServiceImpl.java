package com.wh.service.impl;

import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
import com.wh.mapper.ItemMapper;
import com.wh.pojo.Item;
import com.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<PieceLocationDTO> findItemLocations(String itemID) {
        return itemMapper.findItemLocations(itemID);
    }

    @Override
    public List<Item> list() {
        return itemMapper.list();
    }

    @Override
    public List<OrderItemLocationDTO> findOrderItems(String orderID) {
        return itemMapper.findOrderItems(orderID);
    }
}
