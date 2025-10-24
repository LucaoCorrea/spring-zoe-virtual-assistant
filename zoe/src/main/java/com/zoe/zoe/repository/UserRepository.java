package com.zoe.zoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoe.zoe.model.UserModel;

public interface  UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
}
