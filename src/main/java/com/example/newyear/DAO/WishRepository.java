package com.example.newyear.DAO;

import com.example.newyear.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Integer> {

}
