package com.assignment.signup.dao.repository;


import com.assignment.signup.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
