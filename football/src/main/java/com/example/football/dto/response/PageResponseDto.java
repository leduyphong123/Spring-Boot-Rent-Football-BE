package com.example.football.dto.response;

import java.util.List;

public class PageResponseDto<T> {
    List<T> content;

    int totalPage;

    long totalRecord;

    int currentPage;

    int pageSize;
}
