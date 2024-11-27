package com.wh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String userName;
    private String password;
    private String fname;
    private String lname;
    private String email;
}

