package ru.codekitchen.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codekitchen.entity.Comment;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.repository.CommentRepository;
import ru.codekitchen.repository.ShawarmaPlaceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ShawarmaPlaceRepository shawarmaPlaceRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,ShawarmaPlaceRepository shawarmaPlaceRepository) {
        this.commentRepository = commentRepository;
        this.shawarmaPlaceRepository = shawarmaPlaceRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllByShawarmaPlace_Id(Integer placeId) {
        return commentRepository.findAllByShawarmaPlace_Id(placeId);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteById(Integer id) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if (commentOpt.isEmpty()) {
            throw  new EntityNotFoundException("Comment not found with id " + id);
        }
        commentRepository.delete(commentOpt.get());

    }





}
