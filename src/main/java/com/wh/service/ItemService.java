package com.wh.service;
import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
import com.wh.pojo.Item;
import java.util.List;

public interface ItemService {

    List<Item> list();

    List<PieceLocationDTO> findItemLocations(String itemID);

    List<OrderItemLocationDTO> findOrderItems(String orderID);
}
