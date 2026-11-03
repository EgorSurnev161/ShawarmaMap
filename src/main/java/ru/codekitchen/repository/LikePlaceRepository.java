package ru.codekitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.codekitchen.entity.LikePlace;

@Repository

public interface LikePlaceRepository extends JpaRepository<LikePlace, Integer> {

    int countByShawarmaPlace_IdAndIsLikeTrue(int placeId);

    int countByShawarmaPlace_IdAndIsLikeFalse(int placeId);

    LikePlace findByShawarmaPlace_IdAndUser_Id(int placeId, int userId);
}
