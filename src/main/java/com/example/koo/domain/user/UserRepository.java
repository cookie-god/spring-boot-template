package com.example.koo.domain.user;

import com.example.koo.domain.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByIdAndStatus(int userId, String status);
    long countByEmailAndStatus(String email, String status);
    long countByNicknameAndStatus(String nickname, String status);
}
