package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.JSONData;
import org.example.models.ListDto;
import org.example.services.ListInfoService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final ListInfoService listInfoService;

    @GetMapping(value = "/hello", produces = "application/json")
    public ResponseEntity<JSONData> hello() {


        JSONData<Object> data = new JSONData<>();

        List<Map<String, Object>> list = listInfoService.getList();
        HttpStatus status = HttpStatus.CREATED;
        data.setHttpStatus(status);
        data.setData(list);
        return ResponseEntity.ok().body(data);

    }
}
