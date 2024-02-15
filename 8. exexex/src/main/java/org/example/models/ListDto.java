package org.example.models;


import lombok.Data;

import java.util.List;

@Data
public class ListDto<T> {
    private String code;
    private String msg;
    private int count;
    private List<T> list;
}
