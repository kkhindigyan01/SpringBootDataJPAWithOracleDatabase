package com.kkhindigyan.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkhindigyan.app.dao.UserRepository;
import com.kkhindigyan.app.entities.User;
import com.kkhindigyan.app.exceptions.UserDataAccessException;

@Service
 public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void deleteUserById(int userId) {
		
		System.out.println("Deleting User whose Id ="+userId);
		userRepository.deleteById(userId);
	}

	public void updateUserAgeById(int userId,int newAge) {

		System.out.println("Finding Enitity by Id = "+userId);
		Optional<User> findById = userRepository.findById(userId);
		User user = findById.get();
		user.setAge(newAge);
		
		userRepository.save(user);
	}

	public Iterable<User> findAllUsers() {
	 return	userRepository.findAll();
	}

	public User findUserById(Integer userId) throws UserDataAccessException {
		System.out.println("Finding Enitity by Id = "+userId);
		if(userId == null)
			throw new UserDataAccessException("User Id can't be null");
		
		Optional<User> findById = userRepository.findById(userId);
		if(findById.isPresent())
			return findById.get();
		
		throw new UserDataAccessException("User With ID = "+userId+" Not found in Database");
	}

	public void createUsers(List<User> userList) {
		Iterable<User> dbSavedUsers = userRepository.saveAll(userList);
		System.out.println("Following users info saved in database:");
		dbSavedUsers.forEach(System.out::println);
	}

	public void createUser(User user) {
		User dbUser = userRepository.save(user);
		System.out.println("Following user info saved in database:");
		System.out.println(dbUser);
	}
}
