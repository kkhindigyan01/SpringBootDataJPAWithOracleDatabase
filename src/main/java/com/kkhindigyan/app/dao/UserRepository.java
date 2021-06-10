package com.kkhindigyan.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.kkhindigyan.app.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
