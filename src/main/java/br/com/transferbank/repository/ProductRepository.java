package br.com.transferbank.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.transferbank.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	
	@Query("{'name': ?0}")
	Optional<Product> findByProduct(String product);
}
