package com.wh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieceLocationDTO {

    private String pieceNum;
    private String pDescription;
    private String roomNum;
    private String shelfNum;
    private String shelfDescription;

}
