package com.example.newyear.Service;

import com.example.newyear.DTO.UserDto;
import com.example.newyear.exceptions.UserAlreadyExistException;
import com.example.newyear.models.User;

import java.util.List;

public interface UserService {

    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;

    public List<User> getAllUsers();

    public User getOneUser(int id);

    public void saveUser(User user);

    void saveUpdateOfUser(String name);

    public void deleteUser(int id);

    List<User> getAllUsersExceptCurrentUser();

    User getCurrentUserByName();

}
