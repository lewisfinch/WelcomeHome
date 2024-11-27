package com.wh.pojo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonatedBy {
    private int itemId;
    private String userName;
    private Date donateDate;
}

