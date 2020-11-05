package com.example.plugin;

import lombok.Data;

@Data
public class PageResponse<T> {
    private int totalNumber;
    private int currentPage;
    private int totalPage;
    private int pageSize = 3;
    private T data;

}
