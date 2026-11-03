package ru.codekitchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.codekitchen.entity.LikePlace;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.repository.LikePlaceRepository;
import ru.codekitchen.repository.ShawarmaPlaceRepository;

import java.util.Objects;

@Service
public class LikePlaceService {

    private final LikePlaceRepository likePlaceRepository;
    private final ShawarmaPlaceRepository shawarmaPlaceRepository;

    @Autowired
    public LikePlaceService(LikePlaceRepository likePlaceRepository, ShawarmaPlaceRepository shawarmaPlaceRepository){
        this.likePlaceRepository = likePlaceRepository;
        this.shawarmaPlaceRepository = shawarmaPlaceRepository;
    }

    public void save( User user, ShawarmaPlace place, Boolean switchN) {
        LikePlace likePlace = findByIds(place.getId(), user.getId());

        if (likePlace == null) {
            if (switchN != null){
                likePlaceRepository.save(new LikePlace(user, place, switchN));

                if (switchN) {
                    place.setNumberOfLikes(place.getNumberOfLikes() + 1);
                } else {
                    place.setNumberOfDislikes(place.getNumberOfDislikes() + 1);
                }

                shawarmaPlaceRepository.save(place);

            }else{
                return;
            }
        } else {
            Boolean oldLike = likePlace.getLike();

            if (Objects.equals(oldLike, switchN)) {
                switchN = null;
            }
            likePlace.setLike(switchN);
            likePlaceRepository.save(likePlace);

            if (oldLike != null) {
                if (oldLike) place.setNumberOfLikes(place.getNumberOfLikes() - 1);
                else place.setNumberOfDislikes(place.getNumberOfDislikes() - 1);
            }
            if (switchN != null) {
                if (switchN) place.setNumberOfLikes(place.getNumberOfLikes() + 1);
                else place.setNumberOfDislikes(place.getNumberOfDislikes() + 1);
            }
            shawarmaPlaceRepository.save(place);
        }

    }

    public int countNumbersOfLike(int placeId){
        return likePlaceRepository.countByShawarmaPlace_IdAndIsLikeTrue(placeId);
    }
    public int countNumbersOfDisLike(int placeId){
        return likePlaceRepository.countByShawarmaPlace_IdAndIsLikeFalse(placeId);
    }
    public LikePlace findByIds(int userId, int placeId){
        return likePlaceRepository.findByShawarmaPlace_IdAndUser_Id(userId, placeId);
    }

    public void update(LikePlace likeplace, Boolean switchN){
        likeplace.setLike(switchN);
    }


}
