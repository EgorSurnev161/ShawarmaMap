package ru.codekitchen.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.service.ShawarmaPlaceService;

import java.util.List;

@Controller
public class PublicHomeController {

    private final ShawarmaPlaceService shawarmaPlaceService;

    @Autowired
    public PublicHomeController(ShawarmaPlaceService shawarmaPlaceService) {
        this.shawarmaPlaceService = shawarmaPlaceService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<ShawarmaPlace> places = shawarmaPlaceService.findAll();
        model.addAttribute("places", places);
        return "public/home-page";
    }
}
