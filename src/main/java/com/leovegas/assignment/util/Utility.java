package com.leovegas.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Utility {

    private static ObjectMapper om;

    //TODO remove
    @PostConstruct
    public void init(){
        om = new ObjectMapper();
        om.registerModule(new JSR310Module());
    }

    public static ObjectMapper objectMapper(){
        return om;
    }

}
