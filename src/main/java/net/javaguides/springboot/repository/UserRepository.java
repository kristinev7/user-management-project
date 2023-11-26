package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//used to perform CRUD: Create, Read, Update, Delete
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); /*query method to retrieve user by email*/
}
