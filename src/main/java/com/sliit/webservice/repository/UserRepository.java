package com.sliit.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sliit.webservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}