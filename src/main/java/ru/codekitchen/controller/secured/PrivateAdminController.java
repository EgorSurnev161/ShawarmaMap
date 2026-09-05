package ru.codekitchen.controller.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.entity.UserRole;
import ru.codekitchen.service.ShawarmaPlaceService;
import ru.codekitchen.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PrivateAdminController {

    private final UserService userService;
    private final ShawarmaPlaceService shawarmaPlaceService;


    @Autowired
    public PrivateAdminController(UserService userService, ShawarmaPlaceService shawarmaPlaceService) {
        this.userService = userService;
        this.shawarmaPlaceService = shawarmaPlaceService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        User currentUser = userService.getCurrentUser();

        List<UserRole> rolesToView;

        if(currentUser.getRole().equals(UserRole.SUPER_ADMIN)){
            rolesToView = Arrays.asList(UserRole.USER, UserRole.ADMIN);
        }else{
            rolesToView = List.of(UserRole.USER);
        }

        List<User> candidatesToDelete = userService.getUsersByRole(rolesToView);
        List<ShawarmaPlace> places = shawarmaPlaceService.getPlacesByOneUserRole(rolesToView);

        model.addAttribute("user", currentUser);
        model.addAttribute("candidatesToDelete", candidatesToDelete);
        model.addAttribute("places", places);

        return "private/admin/admin-page";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") int userId) {
        userService.deleteById(userId);
        return "redirect:/";

    }






}
