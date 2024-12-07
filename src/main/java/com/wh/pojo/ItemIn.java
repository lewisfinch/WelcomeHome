package com.wh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemIn {
    private int itemID;
    private int orderID;
    private boolean found;
}
