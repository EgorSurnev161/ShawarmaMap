package ru.codekitchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.repository.ShawarmaPlaceRepository;

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







}
