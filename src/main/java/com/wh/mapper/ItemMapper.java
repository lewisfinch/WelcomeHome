package com.wh.mapper;

import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
import com.wh.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ItemMapper {

    @Select("SELECT * FROM item")
    List<Item> list();

    @Select("SELECT p.pieceNum, p.pDescription, p.roomNum, p.shelfNum, l.shelfDescription " +
            "FROM piece p " +
            "JOIN location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum " +
            "WHERE p.ItemID = #{itemID}")
    List<PieceLocationDTO> findItemLocations(@Param("itemID") String itemID);

    @Select("SELECT i.ItemID, i.iDescription, p.pieceNum, p.pDescription, p.roomNum, p.shelfNum, l.shelfDescription " +
            "FROM ordered o " +
            "JOIN itemIn ii ON o.orderID = ii.orderID " +
            "JOIN item i ON ii.ItemID = i.ItemID " +
            "JOIN piece p ON i.ItemID = p.ItemID " +
            "JOIN location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum " +
            "WHERE o.orderID = #{orderID}")
    List<OrderItemLocationDTO> findOrderItems(@Param("orderID") String orderID);

}
