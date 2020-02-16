package com.ecse428.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecse428.project.fitboi.model.UserProfile;

@Repository
public interface UserRepository extends CrudRepository<UserProfile, String> {
	UserProfile findUserByEmail(String email);
}
