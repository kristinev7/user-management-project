package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//used to perform CRUD: Create, Read, Update, Delete
public interface UserRepository extends JpaRepository<User, Long> {

}
