package com.wh.mapper;

import com.wh.dto.*;
import com.wh.pojo.*;
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

    @Select("SELECT o.orderID, o.orderDate, o.supervisor, o.client, i.itemID, i.iDescription, d.username AS donor, d.donateDate, del.userName as deliveredBy, del.date as deliverDate, del.status " +
            "FROM Ordered o " +
            "LEFT JOIN ItemIn ii ON o.orderID = ii.orderID " +
            "LEFT JOIN Item i ON ii.itemID = i.itemID " +
            "LEFT JOIN Donatedby d ON i.itemID = d.itemID " +
            "LEFT JOIN Delivered del ON o.orderID = del.orderID " +
            "WHERE #{userName} IN (o.supervisor, o.client, d.username, del.userName)")
    List<RelevantOrderDTO> getRelevantOrders(Person person);

    @Select("SELECT i.mainCategory,i.subCategory,COUNT(DISTINCT o.orderID) AS orderCount " +
    "FROM Ordered o " +
    "JOIN ItemIn ii ON o.orderID = ii.orderID " +
    "JOIN Item i ON ii.itemID = i.itemID " +
    "WHERE o.orderDate BETWEEN #{startDate} AND #{endDate} " +
    "GROUP BY i.mainCategory, i.subCategory " +
    "ORDER BY orderCount DESC")
    List<CategoryDTO> getPopularCategories(DateDTO dateDTO);

    @Select("SELECT DISTINCT mainCategory, subCategory " +
            "FROM Category")
    List<Category> getExistingCategory();

}
