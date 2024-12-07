package com.wh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String itemID;
    private String orderID;
    private String supervisor;
    private String client;
    private String orderNotes;
}
