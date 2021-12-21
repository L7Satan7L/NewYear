package com.example.newyear.controllers;


import com.example.newyear.DTO.UserDto;
import com.example.newyear.Service.PresentServiceImpl;
import com.example.newyear.Service.WishServiceImpl;
import com.example.newyear.exceptions.UserAlreadyExistException;
import com.example.newyear.Service.UserServiceImpl;
import com.example.newyear.models.Present;
import com.example.newyear.models.User;
import com.example.newyear.models.Wish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping()
public class UserController {

    private final UserServiceImpl userService;

    private final WishServiceImpl wishService;

    private final PresentServiceImpl presentService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
                                            HttpServletRequest request, Errors errors) {
        ModelAndView mav = null;
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("registration", "registrationForm", userDto);
            return mav;
        }
        try {
            userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("name", "userDto.name", "An account with username " + userDto.getName() + " already exists.");
            mav = new ModelAndView("registration", "registrationForm", userDto);
            return mav;
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<User> users = userService.getAllUsersExceptCurrentUser();
        model.addAttribute("users", users);
        model.addAttribute("bool", true);
        return "main-wish";
    }

    @PostMapping("/profile/addWish")
    public ModelAndView profilePostWishPage(@RequestBody Wish wish) {
        User user = userService.getCurrentUserByName();
        int id = user.getId();
        wish.setUserId(id);
        wish.setGet(false);
        wishService.saveWish(wish);
        return new ModelAndView("profile", "user", user);
    }

    @PutMapping("/getWish")
    public ModelAndView getWishPage(@RequestBody Wish wish) {
        int id = wish.getId();
        Wish wish1 = wishService.getOneWish(id);
        wish1.setGet(true);
        wishService.saveWish(wish1);
        User user = userService.getCurrentUserByName();
        int idUser = user.getId();
        presentService.savePresentWish(wish1, idUser);
        List<User> users = userService.getAllUsersExceptCurrentUser();
        return new ModelAndView("main-wish", "users", users);
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User user = userService.getCurrentUserByName();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public ModelAndView profilePostPage(@ModelAttribute User user) {
        String name = user.getFirstName();
        userService.saveUpdateOfUser(name);
        User user1 = userService.getCurrentUserByName();
        return new ModelAndView("profile", "user", user1);
    }


    @DeleteMapping("/profile/delete")
    @ResponseBody
    public ModelAndView profileDeleteWish(@RequestBody Wish wish) {
        int id = wish.getId();
        wishService.deleteWish(id);
        User user = userService.getCurrentUserByName();
        return new ModelAndView("profile", "user", user);
    }

    @DeleteMapping("/profile/deletePresent")
    @ResponseBody
    public ModelAndView profileDeletePresent(@RequestBody Present present) {
        int id = present.getId();
        Present present1 = presentService.getOnePresent(id);
        Wish wish = wishService.getOneWish(present1.getWishId());
        wishService.saveUpdate(wish);
        presentService.deletePresent(id);
        User user = userService.getCurrentUserByName();
        return new ModelAndView("profile", "user", user);
    }

    @PutMapping("/profile/update")
    public ModelAndView profileUpdateWish(@RequestBody Wish wish) {
        int id = wish.getId();
        String name = wish.getName();
        String wishText = wish.getWish();
        wishService.saveUpdateOfWish(id, name, wishText);
        User user = userService.getCurrentUserByName();
        return new ModelAndView("profile", "user", user);
    }

}

