package ru.codekitchen.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;
import ru.codekitchen.entity.dto.ShawarmaPlaceDto;
import ru.codekitchen.repository.ShawarmaPlaceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShawarmaPlaceService {

    private final ShawarmaPlaceRepository shawarmaPlaceRepository;

    @Autowired
    public ShawarmaPlaceService(ShawarmaPlaceRepository shawarmaPlaceRepository) {
        this.shawarmaPlaceRepository = shawarmaPlaceRepository;
    }

    public Optional<ShawarmaPlace> findById(Integer id) {return  shawarmaPlaceRepository.findById(id);}

    public List<ShawarmaPlace> findAll() {
        return shawarmaPlaceRepository.findAll();
    }

    public void saveShawarmaPlace(ShawarmaPlaceDto dto, User user) {
        ShawarmaPlace shawarmaPlace = ShawarmaPlace.toEntityNew(dto, user);
        shawarmaPlaceRepository.save(shawarmaPlace);
    }

    public void delete(Integer id, User user) {
        Optional<ShawarmaPlace> shawarmaPlaceOpt = shawarmaPlaceRepository.findById(id);

        if (shawarmaPlaceOpt.isEmpty()){
            throw new EntityNotFoundException("ShawarmaPlace not found with id " + id);
        }
        ShawarmaPlace place = shawarmaPlaceOpt.get();
        if (place.getUser().getId() != user.getId()) {
            throw new EntityNotFoundException("The ID did not match");
        }
        shawarmaPlaceRepository.deleteById(id);
    }

    public void update(ShawarmaPlaceDto shawarmaPlaceDto, User user ){
        Optional<ShawarmaPlace> shawarmaPlaceOpt = shawarmaPlaceRepository.findById(shawarmaPlaceDto.getId());

        if (shawarmaPlaceOpt.isEmpty()){
            throw new EntityNotFoundException("ShawarmaPlace not found with id " + shawarmaPlaceDto.getId());
        }
        ShawarmaPlace place = shawarmaPlaceOpt.get();

        if (place.getUser().getId() != user.getId()) {
            throw new EntityNotFoundException("The ID did not match");
        }

        place.setName(shawarmaPlaceDto.getName());
        place.setAddress(shawarmaPlaceDto.getAddress());
        place.setCity(shawarmaPlaceDto.getCity());
        place.setCreateDate(LocalDateTime.now());

        shawarmaPlaceRepository.save(place);

    }









}
