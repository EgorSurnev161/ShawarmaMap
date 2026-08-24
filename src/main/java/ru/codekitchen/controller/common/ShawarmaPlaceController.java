package ru.codekitchen.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.service.ShawarmaPlaceService;

@Controller
public class ShawarmaPlaceController {

    private final ShawarmaPlaceService shawarmaPlaceService;

    @Autowired
    public ShawarmaPlaceController(ShawarmaPlaceService shawarmaPlaceService) {
        this.shawarmaPlaceService = shawarmaPlaceService;
    }

    @GetMapping("/place/{id}")
    public String getPlace(@PathVariable int id,  Model model) {
        ShawarmaPlace place = shawarmaPlaceService.findById(id)
                        .orElseThrow(()  -> new RuntimeException("No place with id " + id));
        model.addAttribute("place", place);
        return "public/place-shawarma";
    }
}
