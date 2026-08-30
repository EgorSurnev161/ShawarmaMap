package ru.codekitchen.controller.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.service.ShawarmaPlaceService;
import ru.codekitchen.service.UserService;

import java.time.LocalDateTime;

@Controller
//@RequestMapping("/account")
public class PrivateAccountController {

    private final ShawarmaPlaceService shawarmaPlaceService;
    private final UserService userService;

    @Autowired
    public PrivateAccountController(ShawarmaPlaceService shawarmaPlaceService, UserService userService) {
        this.shawarmaPlaceService = shawarmaPlaceService;
        this.userService = userService;
    }

    @GetMapping("/add-place-page")
    public String showAddPlace(Model model) {
        model.addAttribute("newPlace", new ShawarmaPlace());
        return "private/add-place-page";
    }


    @PostMapping(value = "/add-place-page")
    public String addPlace(@ModelAttribute("newPlace") ShawarmaPlace place) {

        place.setCreateDate(LocalDateTime.now());
        place.setNumberOfLikes(0);
        place.setNumberOfDislikes(0);

        shawarmaPlaceService.saveShawarmaPlace(place);
        return "redirect:/";
    }

    @GetMapping("/account")
    public String getAccount(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        return "private/account-page";
    }



}
