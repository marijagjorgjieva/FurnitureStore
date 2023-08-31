package com.proekt.dnick.aldehyde.service.impl;

import com.proekt.dnick.aldehyde.constants.ErrorMessage;
import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.repository.FurnitureRepository;
import com.proekt.dnick.aldehyde.service.FurnitureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FurnitureServiceImpl implements FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final ModelMapper modelMapper;

    @Override
    public Furniture getFurnitureById(Long furnitureId) {
        return furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.FURNITURE_NOT_FOUND));
    }

    @Override
    public List<Furniture> getPopularFurnitures() {
        return  this.furnitureRepository.findAll().stream().limit(3).collect(Collectors.toList());

    }

    @Override
    public Page<Furniture> getFurnituresByFilterParams(SearchRequest request, Pageable pageable) {
        Integer startingPrice = request.getPrice();
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 500 : 50);
        return furnitureRepository.getFurnituresByFilterParams(
                request.getBrands(),
                request.getMaterials(),
                startingPrice,
                endingPrice,
                pageable);
    }

    @Override
    public Page<Furniture> searchFurnitures(SearchRequest request, Pageable pageable) {
        return furnitureRepository.searchFurnitures(request.getSearchType(), request.getText(), pageable);
    }
}
