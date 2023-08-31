package com.proekt.dnick.aldehyde.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<String> brands;
    private List<String> materials;
    private Integer price = 0;
    private String searchType;
    private String text;
}
