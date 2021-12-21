package com.example.newyear.DAO;

import com.example.newyear.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByName (String name);

    List<User> findAllByNameNot(String name);

}
