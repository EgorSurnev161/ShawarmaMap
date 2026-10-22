package ru.codekitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.codekitchen.entity.Comment;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByShawarmaPlace_Id(Integer placeId);

}
