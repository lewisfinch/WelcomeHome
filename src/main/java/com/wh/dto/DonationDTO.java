package com.wh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {
    @JsonProperty("itemID")
    private String itemID;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("iDescription")
    private String iDescription;
    @JsonProperty("color")
    private String color;
    @JsonProperty("isNew")
    private boolean isNew;
    @JsonProperty("hasPieces")
    private boolean hasPieces;
    @JsonProperty("material")
    private String material;
    @JsonProperty("mainCategory")
    private String mainCategory;
    @JsonProperty("subCategory")
    private String subCategory;
    @JsonProperty("pieces")
    private List<PieceDTO> pieces;
}
