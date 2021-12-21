package com.example.newyear.DAO;

import com.example.newyear.models.Present;
import com.example.newyear.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentRepository extends JpaRepository<Present, Integer> {
}
