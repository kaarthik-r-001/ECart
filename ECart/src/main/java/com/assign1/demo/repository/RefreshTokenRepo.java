package com.assign1.demo.repository;

import com.assign1.demo.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEntity, Long> {

    @Query(value = "SELECT rt.* FROM REFRESH_TOKENS rt " +
            "INNER JOIN USER_DETAILS ud ON rt.user_id = ud.id " +
            "WHERE ud.Username = :userEmail and rt.revoked = false ", nativeQuery = true)
    List<RefreshTokenEntity> findAllRefreshTokenByUserId(String userEmail);
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);


}