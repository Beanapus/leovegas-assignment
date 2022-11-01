package com.leovegas.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.stereotype.Service;

@Service
public class Utility {

    private static ObjectMapper om;

    public static void init(){
        om = new ObjectMapper();
        //TODO remove
        om.registerModule(new JSR310Module());
    }

    public static ObjectMapper objectMapper(){
        return om;
    }

}
