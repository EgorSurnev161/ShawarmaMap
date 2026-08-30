package ru.codekitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.codekitchen.entity.User;
import ru.codekitchen.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByRoleInOrderById(Iterable<UserRole> roles);

    Optional<User> findByEmailIgnoreCase(String email);



}
