package com.codingdojo.bookclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.bookclub.models.User;

public interface UserRepo extends CrudRepository<User, Long>{
	List<User> findAll();
	Optional<User> findByEmail(String email);
}
