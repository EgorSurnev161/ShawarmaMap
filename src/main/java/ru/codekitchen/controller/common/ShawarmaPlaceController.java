package ru.codekitchen.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.codekitchen.entity.Comment;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.service.CommentService;
import ru.codekitchen.service.LikePlaceService;
import ru.codekitchen.service.ShawarmaPlaceService;
import ru.codekitchen.service.UserService;

import java.util.Optional;

@Controller
public class ShawarmaPlaceController {

    private final ShawarmaPlaceService shawarmaPlaceService;
    private final UserService userService;
    private final CommentService commentService;
    private final LikePlaceService likePlaceService;

    @Autowired
    public ShawarmaPlaceController(ShawarmaPlaceService shawarmaPlaceService, UserService userService, CommentService commentService, LikePlaceService likePlaceService) {
        this.shawarmaPlaceService = shawarmaPlaceService;
        this.userService = userService;
        this.commentService = commentService;
        this.likePlaceService = likePlaceService;
    }

    @GetMapping("/place/{id}")
    public String getPlace(@PathVariable int id,  Model model) {
        ShawarmaPlace place = shawarmaPlaceService.findById(id)
                        .orElseThrow(()  -> new RuntimeException("No place with id " + id));
        model.addAttribute("place", place);
        model.addAttribute("comments", commentService.findAllByShawarmaPlace_Id(id));
        return "public/place-shawarma";
    }

    @PostMapping("/place/{id}/add-comment")
    public String addComment(@PathVariable int id,
                             @RequestParam("comment") String text,
                             Model model) {
        if (text.isEmpty()){
            return "redirect:/place/" + id;
        }
        User user = userService.getCurrentUser();
        ShawarmaPlace place = shawarmaPlaceService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));;

        commentService.save(new Comment(user, text, place));

        return "redirect:/place/" + id;

    }

    @PostMapping("/place/{placeId}/delete-comment")
    public String deleteComment(@PathVariable("placeId") int placeId,
                                @RequestParam("commentId") int commentId) {

        commentService.deleteById(commentId);
        return "redirect:/place/" + placeId;
    }

    @PostMapping("/place/{placeId}/add-like")
    public String addLike(@PathVariable("placeId") int placeId,
                          @RequestParam("switchN") Boolean switchN){
        ShawarmaPlace shawarmaPlace = shawarmaPlaceService.findById(placeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
        User user = userService.getCurrentUser();

        likePlaceService.save(user, shawarmaPlace, switchN);
        return  "redirect:/place/" + placeId;
    }
}
