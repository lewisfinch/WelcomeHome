package com.wh.controller;

import com.wh.pojo.Person;
import com.wh.pojo.Result;
import com.wh.service.PersonService;
import com.wh.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private PersonService personService;

    @PostMapping("/login")
    public Result login(@RequestBody Person person) {

        log.info("Person login: {}", person);
        Person u = personService.login(person);

        // Login success
        if(u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userName", u.getUserName());
            claims.put("fname", u.getFname());
            claims.put("lname", u.getLname());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("jwt: {}", jwt);

            return Result.success(jwt);

        }

        return Result.error("Invalid username or password");

    }
}
