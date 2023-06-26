package com.codewithyad.blog.repositores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithyad.blog.entities.User;

public interface UserRepositories extends JpaRepository<User,Integer> {
	Optional<User> findByEmail(String email);
}
