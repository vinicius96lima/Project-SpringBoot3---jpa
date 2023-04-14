package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;


// @Component // Anotação que serve para registro no nosso Spring,para ser utilizado em outras classes.
// @Repository // Anotação para registrar repositorio

@Service // Anotação para registrar um service. 
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List <User> findAll(){
		return repository.findAll(); 
	}
	
	public User findById (Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
		
	}
	
	public User insert (User obj) {
		return repository.save(obj);
	}
	public void delete (Long id) {
		repository.deleteById(id);
	}
	
}
