package com.rit.smartcontact.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rit.smartcontact.templates.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query("select u from users u where u.email = :email")
	public User getUserByUserName(@Param("email") String email);
}
