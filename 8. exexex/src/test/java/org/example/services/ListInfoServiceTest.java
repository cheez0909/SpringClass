package org.example.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class ListInfoServiceTest {


    @Autowired
    private ListInfoService listInfoService;

    @Test
    void test(){
        listInfoService.getLists();
    }
}