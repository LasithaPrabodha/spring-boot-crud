package com.sliit.webservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sliit.webservice.exception.ResourceNotFoundException;
import com.sliit.webservice.model.User;
import com.sliit.webservice.repository.UserRepository;

@Service
public class UserDAO {
	@Autowired
	UserRepository userRepository;

	/* SAVE AN USER */
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public User save(User emp) {
		return userRepository.save(emp);
	}

	/* SEARCH ALL USERS */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/* GET AN USER */
	public User findOne(Long empId) throws ResourceNotFoundException {
		Optional<User> op = userRepository.findById(empId);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new ResourceNotFoundException("User not found for this id :: " + empId);
		}
	}

	/* DELETE AN USER by id */
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void delete(User emp) {
		userRepository.delete(emp);
	}
}
