package com.wh.service;
import com.wh.pojo.Person;


public interface PersonService {

    Person login(Person person);

    String hashPassword(String password);

}
