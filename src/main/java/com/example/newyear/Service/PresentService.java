package com.example.newyear.Service;

import com.example.newyear.models.Present;
import com.example.newyear.models.Wish;

import java.util.List;

public interface PresentService {

    public List<Present> getAllPresents();

    public Present getOnePresent(int id);

    public void savePresent(Present present);

    public void deletePresent(int id);

    void savePresentWish(Wish wish, int idUser);
}
