package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Product;
import com.example.course.repositories.ProductRepository;


// @Component // Anotação que serve para registro no nosso Spring,para ser utilizado em outras classes.
// @Repository // Anotação para registrar repositorio

@Service // Anotação para registrar um service. 
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List <Product> findAll(){
		return repository.findAll(); 
	}
	
	public Product findById (Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
		
	}
	
}
