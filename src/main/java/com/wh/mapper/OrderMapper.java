package com.wh.mapper;

import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
import com.wh.pojo.Category;
import com.wh.pojo.Item;
import com.wh.pojo.ItemIn;
import com.wh.pojo.Ordered;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderMapper {

    @Select("SELECT COUNT(*) " +
            "FROM Act " +
            "JOIN Role ON Act.roleID = Role.roleID " +
            "WHERE Act.userName = #{userName} AND Role.rDescription = 'client'")
    int checkUserName(@Param("userName") String userName);

    @Select("SELECT COALESCE(MAX(Ordered.orderID), 0) FROM Ordered")
    int newOrder();

    @Select("SELECT Item.ItemID, Item.iDescription, Item.photo, Item.color, Item.isNew, Item.hasPieces, Item.material " +
            "FROM Item " +
            "WHERE Item.MainCategory = #{mainCategory} AND Item.SubCategory = #{subCategory} " +
            "AND Item.ItemID NOT IN (SELECT ItemIn.ItemID FROM ItemIn)")
    List<Item> getCategory(Category category);

    @Insert("INSERT INTO Ordered (orderID, orderDate, orderNotes, supervisor, client) " +
            "VALUES (#{orderID}, #{orderDate}, #{orderNotes}, #{supervisor}, #{client})")
    boolean addToOrder(Ordered ordered);

    @Insert("INSERT INTO Itemin (itemID, orderID, found) " +
            "VALUES (#{itemID}, #{orderID}, #{found})")
    boolean addToItemIn(ItemIn itemIn);
}
