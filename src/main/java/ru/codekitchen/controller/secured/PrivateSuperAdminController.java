package ru.codekitchen.controller.secured;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.codekitchen.entity.User;
import ru.codekitchen.entity.UserRole;
import ru.codekitchen.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/super-admin")
public class PrivateSuperAdminController {

    private final UserService userService;

    @Autowired
    public PrivateSuperAdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/promote-user")
    public String promoteUser(@RequestParam("id") int id) {
        Optional<User> userUpgateToOptional = userService.findById(id);

        if (userUpgateToOptional.isEmpty()) {
            return "redirect:/admin";
        }

        User userToBeUpdated = userUpgateToOptional.get();
        if (userToBeUpdated.getRole() ==  UserRole.ADMIN) {
            return "redirect:/admin";
        }

        userService.updateRole(id, UserRole.ADMIN);

        return "redirect:/admin";
    }
    @PostMapping("/demote-user")
    public String demoteUser(@RequestParam("id") int id) {
        Optional<User> userUpgateToOptional = userService.findById(id);

        if (userUpgateToOptional.isEmpty()) {
            return "redirect:/admin";
        }

        User userToBeUpdated = userUpgateToOptional.get();
        if (userToBeUpdated.getRole() ==  UserRole.USER) {
            return "redirect:/admin";
        }

        userService.updateRole(id, UserRole.USER);

        return "redirect:/admin";
    }



}
