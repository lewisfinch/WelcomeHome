package com.wh.pojo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordered {
    private int orderID;
    private Date orderDate;
    private String orderNotes;
    private String supervisor;
    private String client;
}
