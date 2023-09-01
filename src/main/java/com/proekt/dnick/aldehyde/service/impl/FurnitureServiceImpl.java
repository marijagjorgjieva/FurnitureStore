package com.proekt.dnick.aldehyde.service.impl;

import com.proekt.dnick.aldehyde.configs.constants.ErrorMessage;
import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.model.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.repository.FurnitureRepository;
import com.proekt.dnick.aldehyde.service.FurnitureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 999999 : 6000);
        List<String> lista=new ArrayList<>();
        lista.add("Дневни");
        lista.add("Кујни");
        lista.add("Спални");
        lista.add("Детски");
        lista.add("Претсобја");
        if (request.getBrands()==null)
            request.setBrands(lista);
        List<String> lista2=new ArrayList<>();
        lista2.add("Иверка");
        lista2.add("Фурниран медијапан");
        lista2.add("Масивно дрво");
        lista2.add("Кожа");
        lista2.add("Челик");
        lista2.add("Поликарбонат :D");


        if (request.getMaterials()==null)
            request.setMaterials(lista2);

       List<Furniture>returned= furnitureRepository.findByBrandInAndMaterialInAndPriceBetween(
               request.getBrands(),
               request.getMaterials(),
               startingPrice,
               endingPrice);

        return new PageImpl<Furniture>(returned,pageable,returned.size());    }

    @Override
    public Page<Furniture> searchFurnitures(SearchRequest request, Pageable pageable) {
        return furnitureRepository.searchFurnitures(request.getSearchType(), request.getText(), pageable);
    }
}
