package com.wh.service.impl;

import com.wh.mapper.PersonMapper;
import com.wh.pojo.Person;
import com.wh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person login(Person person) {
        return personMapper.getByUsernameAndPassword(person);
    }
}
