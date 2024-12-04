package com.wh.mapper;

import com.wh.dto.OrderItemLocationDTO;
import com.wh.dto.PieceLocationDTO;
import com.wh.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface DonationMapper {

    @Select("SELECT COUNT(*) " +
            "FROM Act " +
            "JOIN Role ON Act.roleID = Role.roleID " +
            "WHERE Act.userName = #{userName} AND Role.rDescription = 'staff'")
    int isStaff(@Param("userName") String userName);

    @Select("SELECT COUNT(*) " +
            "FROM Act " +
            "JOIN Role ON Act.roleID = Role.roleID " +
            "WHERE Act.userName = #{userName} AND Role.rDescription = 'donor'")
    int isDonor(@Param("userName") String userName);

    @Insert("INSERT INTO Item (itemid, iDescription, color, isnew, haspieces, material, maincategory, subcategory)  " +
            "VALUES (#{itemID}, #{iDescription}, #{color}, #{isNew}, #{hasPieces}, #{material}, #{mainCategory}, #{subCategory})")
    void insertItemWithId(Item item);

    @Insert("INSERT INTO donatedby(itemid, username, donatedate) " +
            "VALUES (#{itemID}, #{userName}, #{donateDate})")
    void insertDonatedBy(DonatedBy donatedBy);

    @Insert("INSERT INTO piece(itemID, pieceNum, pDescription, length, width, height, roomNum, shelfNum, pNotes) " +
            "VALUES (#{itemID}, #{pieceNum}, #{pDescription}, #{length}, #{width}, #{height}, #{roomNum}, #{shelfNum}, #{pNotes})")
    void insertPiece(Piece piece);

    @Select("SELECT COUNT(*) " +
            "FROM Item " +
            "WHERE Item.itemID = #{id}")
    int checkIdUnique(@Param("id") int id);

    @Select("SELECT COUNT(*) " +
            "FROM category " +
            "WHERE category.mainCategory = #{mainCategory} AND category.subCategory = #{subCategory}")
    int checkNewCategory(Category category);

    @Select("SELECT COUNT(*) " +
            "FROM location " +
            "WHERE location.roomNum = #{roomNum} AND location.shelfNum = #{shelfNum}")
    int checkNewLocation(Location location);

    @Insert("INSERT INTO category(mainCategory, subCategory) " +
            "VALUES (#{mainCategory}, #{subCategory})")
    void insertCategory(Category category);

    @Insert("INSERT INTO location(roomNum, shelfNum) " +
            "VALUES (#{roomNum}, #{shelfNum})")
    void insertLocation(Location location);

    @Select("SELECT MAX(Item.itemID) FROM Item")
    int getMaxItemID();
}
