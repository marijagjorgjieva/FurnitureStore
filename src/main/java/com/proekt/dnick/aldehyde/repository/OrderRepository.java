package com.proekt.dnick.aldehyde.repository;

import com.proekt.dnick.aldehyde.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"furnitures", "user", "user.roles"})
    Page<Order> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"furnitures", "user", "user.roles"})
    Optional<Order> getById(Long orderId);

    @EntityGraph(attributePaths = {"furnitures"})
    Optional<Order> getByIdAndUserId(Long orderId, Long userId);

    @EntityGraph(attributePaths = {"furnitures", "user", "user.roles"})
    Page<Order> findOrderByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"furnitures", "user", "user.roles"})
    @Query("SELECT orders FROM Order orders WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'firstName' THEN UPPER(orders.firstName) " +
            "   WHEN :searchType = 'lastName' THEN UPPER(orders.lastName) " +
            "   ELSE UPPER(orders.email) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%'))")
    Page<Order> searchOrders(String searchType, String text, Pageable pageable);

    @EntityGraph(attributePaths = {"furnitures", "user", "user.roles"})
    @Query("SELECT orders FROM Order orders " +
            "LEFT JOIN orders.user user " +
            "WHERE user.id = :userId " +
            "AND (CASE " +
            "   WHEN :searchType = 'firstName' THEN UPPER(orders.firstName) " +
            "   WHEN :searchType = 'lastName' THEN UPPER(orders.lastName) " +
            "   ELSE UPPER(orders.email) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%'))")
    Page<Order> searchUserOrders(Long userId, String searchType, String text, Pageable pageable);
}
