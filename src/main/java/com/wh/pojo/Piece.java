package com.wh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Piece {
    private int itemID;
    private int pieceNum;
    private String pDescription;
    private int length;
    private int width;
    private int height;
    private int roomNum;
    private int shelfNum;
    private String pNotes;
}
