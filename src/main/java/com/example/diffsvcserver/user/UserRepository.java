package com.example.diffsvcserver.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUserId(String userId);

    @Modifying
    @Query("UPDATE User u SET u.selectModel = :model WHERE u.id = :userId")
    void applyModel(Long userId, String model);
}
