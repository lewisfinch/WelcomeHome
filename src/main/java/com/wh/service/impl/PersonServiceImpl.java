package com.wh.service.impl;

import com.wh.mapper.PersonMapper;
import com.wh.pojo.Person;
import com.wh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person login(Person person) {
        String salt = person.getUserName() + "WELCOME_HOME";
        String hashedPassword = hashPassword(person.getPassword(), salt);

        Person user = new Person();
        user.setUserName(person.getUserName());
        user.setPassword(hashedPassword);
        System.out.println(hashedPassword);

        return personMapper.getByUsernameAndPassword(user);
    }
//    public Person login(Person person) {
//        Person user = new Person();
//        user.setUserName(person.getUserName());
//        user.setPassword(person.getPassword());
//        return personMapper.getByUsernameAndPassword(user);
//    }

    @Override
    public String hashPassword(String password, String salt) {
        try {
            String saltedPassword = salt + password;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
