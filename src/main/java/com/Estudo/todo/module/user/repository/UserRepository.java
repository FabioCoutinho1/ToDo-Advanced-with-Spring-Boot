package com.estudo.todo.module.user.repository;


import com.estudo.todo.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findById(long id);

    UserDetails findByUserName(String username);

}
