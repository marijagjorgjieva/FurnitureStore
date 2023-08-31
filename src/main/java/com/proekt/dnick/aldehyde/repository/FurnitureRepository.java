package com.proekt.dnick.aldehyde.repository;

import com.proekt.dnick.aldehyde.model.Furniture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

    List<Furniture> findByIdIn(List<Long> furnituresIds);

    Page<Furniture> findAllByOrderByPriceAsc(Pageable pageable);

    @Query("SELECT furniture FROM Furniture furniture WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'furnitureTitle' THEN UPPER(furniture.furnitureTitle) " +
            "   WHEN :searchType = 'country' THEN UPPER(furniture.country) " +
            "   ELSE UPPER(furniture.brand) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY furniture.price ASC")
    Page<Furniture> searchFurnitures(String searchType, String text, Pageable pageable);

    @Query("SELECT furniture FROM Furniture furniture " +
            "WHERE (coalesce(:brands, null) IS NULL OR furniture.brand IN :brands) " +
            "AND (coalesce(:materials, null) IS NULL OR furniture.material IN :materials) " +
            "AND (coalesce(:priceStart, null) IS NULL OR furniture.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY furniture.price ASC")
    Page<Furniture> getFurnituresByFilterParams(
            List<String> brands,
            List<String> materials,
            Integer priceStart,
            Integer priceEnd,
            Pageable pageable);

}
