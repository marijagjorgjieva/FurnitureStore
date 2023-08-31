package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FurnitureService {

    Furniture getFurnitureById(Long furnitureId);

    List<Furniture> getPopularFurnitures();

    Page<Furniture> getFurnituresByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Furniture> searchFurnitures(SearchRequest searchRequest, Pageable pageable);

}
