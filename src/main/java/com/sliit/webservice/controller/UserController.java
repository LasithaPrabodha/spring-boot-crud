package com.sliit.webservice.controller;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.webservice.dao.UserDAO;
import com.sliit.webservice.exception.ResourceNotFoundException;
import com.sliit.webservice.model.User;

@RestController
@RequestMapping("/accounts")
public class UserController {

	@Autowired
	UserDAO userDAO;

	/* SAVE AN USER */
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userDAO.save(user); 
	}

	/* GET ALL USERS */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	/* GET USER BY ID */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long empId) throws ResourceNotFoundException{
		User user = userDAO.findOne(empId);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	/* UPDATE AN USER BY ID */
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {

		User user = userDAO.findOne(userId);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setName(userDetails.getName());
		user.setPhoneNumber(userDetails.getPhoneNumber());
		user.setEmail(userDetails.getEmail());
		user.setUserID(userDetails.getUserID());

		User updateUser = userDAO.save(user);

		return ResponseEntity.ok().body(updateUser);
	}

	/* DELETE AN USER */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userID) throws ResourceNotFoundException {
		User user = userDAO.findOne(userID);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		userDAO.delete(user);
		return ResponseEntity.ok().build();
	}
}