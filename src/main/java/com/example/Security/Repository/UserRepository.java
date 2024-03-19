package com.example.Security.Repository;

import com.example.Security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    static User findByUsername(String username) {
        return null;
    }

    List<User> findByStatusTrue();

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
