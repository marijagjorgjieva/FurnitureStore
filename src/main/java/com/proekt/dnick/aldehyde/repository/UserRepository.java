package com.proekt.dnick.aldehyde.repository;

import com.proekt.dnick.aldehyde.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "roles")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "roles")
    User findByEmail(String email);



    @EntityGraph(attributePaths = "roles")
    @Query("SELECT user FROM User user WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'firstName' THEN UPPER(user.firstName) " +
            "   WHEN :searchType = 'lastName' THEN UPPER(user.lastName) " +
            "   ELSE UPPER(user.email) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%'))")
    Page<User> searchUsers(String searchType, String text, Pageable pageable);
}
