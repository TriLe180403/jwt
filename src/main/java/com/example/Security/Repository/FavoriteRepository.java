package com.example.Security.Repository;

import com.example.Security.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository  extends JpaRepository<Favorite, Integer> {
}
