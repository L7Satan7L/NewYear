package com.example.newyear.Service;

import com.example.newyear.DAO.PresentRepository;
import com.example.newyear.DAO.WishRepository;
import com.example.newyear.models.Present;
import com.example.newyear.models.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresentServiceImpl implements PresentService{

    @Autowired
    private PresentRepository presentRepository;

    @Override
    public List<Present> getAllPresents() {
        return presentRepository.findAll();
    }

    @Override
    public Present getOnePresent(int id) {
        Present present = null;
        Optional<Present> optional = presentRepository.findById(id);
        if (optional.isPresent()) {
            present = optional.get();
        }
        return present;
    }

    @Override
    public void savePresent(Present present) {
        presentRepository.save(present);
    }

    @Override
    public void deletePresent(int id) {
        presentRepository.deleteById(id);
    }

    @Override
    public void savePresentWish(Wish wish, int idUser) {
        Present present = new Present();
        present.setName(wish.getName());
        present.setWish(wish.getWish());
        present.setGet(wish.getGet());
        present.setWishId(wish.getId());
        present.setUserId(idUser);
        presentRepository.save(present);
    }

    //    @Override
//    public void saveUpdateOfWish(int id, String name, String wish) {
//        Wish wish1 = wishRepository.getById(id);
//        wish1.setName(name);
//        wish1.setWish(wish);
//        wishRepository.save(wish1);
//    }
}
