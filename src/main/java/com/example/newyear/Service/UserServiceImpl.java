package com.example.newyear.Service;

import com.example.newyear.DAO.UserRepository;
import com.example.newyear.DTO.UserDto;
import com.example.newyear.exceptions.UserAlreadyExistException;
import com.example.newyear.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean nameExist(String name) {
        return userRepository.findUserByName(name) != null;
    }

    private void encodePassword( User user, UserDto userDto){
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }

    @Override
    public List<User> getAllUsersExceptCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<User> users = userRepository.findAllByNameNot(name);
        return users;
    }

    @Override
    public User getCurrentUserByName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findUserByName(name);
        return user;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (nameExist(userDto.getName())) {
            throw new UserAlreadyExistException("Account with name: "
                    + userDto.getName() + " already exists");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setFirstName(userDto.getFirstName());
        encodePassword(user, userDto);
        user.setRole("ROLE_USER");
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveUpdateOfUser(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name1 = auth.getName();
        User user = userRepository.findUserByName(name1);
        user.setFirstName(name);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
