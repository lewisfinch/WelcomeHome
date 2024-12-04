package com.wh.service.impl;

import com.wh.dto.DonationDTO;
import com.wh.dto.PieceDTO;
import com.wh.mapper.DonationMapper;
import com.wh.pojo.*;
import com.wh.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;


@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationMapper donationMapper;

    @Override
    public boolean isStaff(String userName) {
        int count = donationMapper.isStaff(userName);
        System.out.println(userName);
        return count > 0;
    }

    @Override
    public boolean isDonor(String userName) {
        int count = donationMapper.isDonor(userName);
        return count > 0;
    }

    @Override
    public void acceptDonations(DonationDTO donationDto) {

        Category category = new Category();
        category.setMainCategory(donationDto.getMainCategory());
        category.setSubCategory(donationDto.getSubCategory());
        int newCategory = donationMapper.checkNewCategory(category);
        if(newCategory < 1) {
            donationMapper.insertCategory(category);
        }

        Item item = new Item();
        item.setIDescription(donationDto.getIDescription());
        item.setColor(donationDto.getColor());
        item.setNew(donationDto.isNew());
        item.setHasPieces(donationDto.isHasPieces());
        item.setMaterial(donationDto.getMaterial());
        item.setMainCategory(donationDto.getMainCategory());
        item.setSubCategory(donationDto.getSubCategory());
        int idUnique = donationMapper.checkIdUnique(Integer.parseInt(donationDto.getItemID()));

        if(donationDto.getItemID() != null && idUnique < 1) {
            item.setItemID(Integer.parseInt(donationDto.getItemID()));
            donationMapper.insertItemWithId(item);
        } else {
            int newId = donationMapper.getMaxItemID() + 1;
            item.setItemID(newId);
            donationMapper.insertItemWithId(item);
        }

        Date current = Date.valueOf(LocalDate.now());
        int itemId = item.getItemID();

        DonatedBy donatedBy = new DonatedBy();
        donatedBy.setItemID(itemId);
        donatedBy.setUserName(donationDto.getUserName());
        donatedBy.setDonateDate(current);
        donationMapper.insertDonatedBy(donatedBy);

        if (donationDto.isHasPieces() && donationDto.getPieces() != null) {
            int pieceNum = 1;
            for (PieceDTO pieceDto : donationDto.getPieces()) {
                Piece piece = new Piece();
                piece.setItemID(itemId);
                piece.setPieceNum(pieceNum++);
                piece.setPDescription(pieceDto.getPDescription());
                piece.setLength(pieceDto.getLength());
                piece.setWidth(pieceDto.getWidth());
                piece.setHeight(pieceDto.getHeight());
                piece.setPNotes(pieceDto.getPNotes());
                piece.setRoomNum(Integer.parseInt(pieceDto.getRoomNum()));
                piece.setShelfNum(Integer.parseInt(pieceDto.getShelfNum()));

                Location location = new Location();
                location.setRoomNum(Integer.parseInt(pieceDto.getRoomNum()));
                location.setShelfNum(Integer.parseInt(pieceDto.getShelfNum()));
                int newLocation = donationMapper.checkNewLocation(location);
                if(newLocation < 1){
                    donationMapper.insertLocation(location);
                }

                donationMapper.insertPiece(piece);
            }
        }
    }
}
