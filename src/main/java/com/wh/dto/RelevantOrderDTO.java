package com.wh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelevantOrderDTO {
    private String orderID;
    private String orderDate;
    private String supervisor;
    private String client;
    private String itemID;
    private String iDescription;
    private String donor;
    private Date donateDate;
    private String deliveredBy;
    private String deliveredDate;
    private String status;
}
