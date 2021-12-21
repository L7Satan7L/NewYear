package com.example.newyear.Service;

import com.example.newyear.DAO.WishRepository;
import com.example.newyear.models.User;
import com.example.newyear.models.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishServiceImpl implements WishService {

    @Autowired
    private WishRepository wishRepository;

    @Override
    public List<Wish> getAllWishes() {
        return wishRepository.findAll();
    }

    @Override
    public Wish getOneWish(int id) {
        Wish wish = null;
        Optional<Wish> optional = wishRepository.findById(id);
        if (optional.isPresent()) {
            wish = optional.get();
        }
        return wish;
    }

    @Override
    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    @Override
    public void deleteWish(int id) {
        wishRepository.deleteById(id);
    }

    @Override
    public void saveUpdateOfWish(int id, String name, String wish) {
        Wish wish1 = wishRepository.getById(id);
        wish1.setName(name);
        wish1.setWish(wish);
        wishRepository.save(wish1);
    }

    @Override
    public void saveUpdate(Wish wish) {
        Wish wish1 = wishRepository.getById(wish.getId());
        wish1.setGet(false);
        wishRepository.save(wish1);
    }
}
