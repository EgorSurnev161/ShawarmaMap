package ru.codekitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.codekitchen.entity.ShawarmaPlace;


@Repository
public interface ShawarmaPlaceRepository extends JpaRepository<ShawarmaPlace, Integer> {



}

