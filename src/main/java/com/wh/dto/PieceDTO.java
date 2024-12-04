package com.wh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieceDTO {
    @JsonProperty("pDescription")
    private String pDescription;
    @JsonProperty("length")
    private int length;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;
    @JsonProperty("roomNum")
    private String roomNum;
    @JsonProperty("shelfNum")
    private String shelfNum;
    @JsonProperty("pNotes")
    private String pNotes;
}
