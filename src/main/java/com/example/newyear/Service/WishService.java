package com.example.newyear.Service;

import com.example.newyear.models.Wish;

import java.util.List;

public interface WishService {

    public List<Wish> getAllWishes();

    public Wish getOneWish(int id);

    public void saveWish(Wish wish);

    public void deleteWish(int id);

    public void saveUpdateOfWish(int id,String name, String wish);

    void saveUpdate(Wish wish);

}
