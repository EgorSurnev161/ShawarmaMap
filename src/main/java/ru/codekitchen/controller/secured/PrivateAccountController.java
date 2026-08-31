package ru.codekitchen.controller.secured;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.entity.dto.ShawarmaPlaceDto;
import ru.codekitchen.service.ShawarmaPlaceService;
import ru.codekitchen.service.UserService;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/account")
public class PrivateAccountController {

    private final ShawarmaPlaceService shawarmaPlaceService;
    private final UserService userService;

    @Autowired
    public PrivateAccountController(ShawarmaPlaceService shawarmaPlaceService, UserService userService) {
        this.shawarmaPlaceService = shawarmaPlaceService;
        this.userService = userService;
    }

    @GetMapping("/add-place")
    public String showAddPlace(Model model) {
        model.addAttribute("newPlace", new ShawarmaPlace());
        return "private/add-place-page";
    }


    @PostMapping("/add-place")
    public String addPlace(@ModelAttribute("newPlace") ShawarmaPlaceDto dto) {
        User currentUser  = userService.getCurrentUser();

        shawarmaPlaceService.saveShawarmaPlace(dto, currentUser);
        return "redirect:/";
    }

    @GetMapping
    public String getAccount(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        return "private/account-page";
    }

    @PostMapping("/delete-place")
    public String deletePlace(@RequestParam int id) {
        User currentUser = userService.getCurrentUser();
        shawarmaPlaceService.delete(id, currentUser);
        return "redirect:/";
    }

    @GetMapping("/update-place/{id}")
    public String updatePlace(@PathVariable int id, Model model) {
        ShawarmaPlace place = shawarmaPlaceService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        model.addAttribute("placeDto", ShawarmaPlaceDto.toDto(place));
        return "private/update-place-page";

    }

    @PostMapping("/update-place")
    public String updatePlace(@ModelAttribute("placeDto") ShawarmaPlaceDto dto) {
        User currentUser = userService.getCurrentUser();

        shawarmaPlaceService.update(dto, currentUser);

        return "redirect:/";
    }



}
