package com.rit.smartcontact.persistence;

import org.springframework.data.repository.CrudRepository;

import com.rit.smartcontact.templates.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
