package ru.codekitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.UserRole;

import java.util.List;


@Repository
public interface ShawarmaPlaceRepository extends JpaRepository<ShawarmaPlace, Integer> {

    List<ShawarmaPlace> findAllByUserRoleIn(Iterable<UserRole> roles);

}

