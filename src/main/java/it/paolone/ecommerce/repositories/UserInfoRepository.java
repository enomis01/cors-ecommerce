package it.paolone.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.paolone.ecommerce.entities.User;

@Repository
public interface UserInfoRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user_info WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM user_info WHERE email = ?1", nativeQuery = true)
    int deleteByEmail(String email);

//     @Modifying
//     @Query("UPDATE user_info u SET u.email = :newEmail WHERE u.email = :actualEmail")
//     int updateUserEmail(@Param("actualEmail") String actualEmail, @Param("newEmail") String newEmail);

//     @Modifying
//     @Query("UPDATE user_info u SET u.password = :newPassword WHERE u.password = :actualPassword")
//     int updateUserPassword(@Param("actualPassword") String actualPassword, @Param("newPassword") String newPassword);

// }
}
