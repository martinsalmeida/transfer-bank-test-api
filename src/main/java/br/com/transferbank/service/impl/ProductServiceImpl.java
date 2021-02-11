package br.com.transferbank.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferbank.entity.Product;
import br.com.transferbank.exception.ProductCollectionException;
import br.com.transferbank.repository.ProductRepository;
import br.com.transferbank.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAllProducts() {
		List<Product> products = productRepository.findAll();
		if(products.size() > 0) {
			return products;
		}else {
			return new ArrayList<Product>();
		}
	}

	@Override
	public Product findProductByID(String id) throws ProductCollectionException {
		Optional<Product> productOptional = productRepository.findById(id);
		if(!productOptional.isPresent()) {
			throw new ProductCollectionException(ProductCollectionException.NotFoundException(id));
		}else {
			return productOptional.get();
		}
	}

	@Override
	public Product createProduct(Product product) throws ProductCollectionException {
		Optional<Product> productOptional = productRepository.findByProduct(product.getName());
		
		
		
		if(productOptional.isPresent()) {
			throw new ProductCollectionException(ProductCollectionException.ProductAlreadyExists());
		}else if (product.getName().isEmpty() || product.getDolar_price() < 0 || product.getReal_price() < 0) {
			throw new ProductCollectionException(ProductCollectionException.FieldEmpty());
		} else {
			product.setCreate_at(LocalDateTime.now());
			return productRepository.save(product);
		} 
			
		
	}

	@Override
	public Product updateProduct(String id, Product product) throws ProductCollectionException{
		
		Optional<Product> productWithId = productRepository.findById(id);
		Optional<Product>  productWithSameName = productRepository.findByProduct(product.getName());
		
		if(productWithId.isPresent()) {
			
			if( productWithSameName.isPresent() && !productWithSameName.get().getId().equals(id)) {
				
				throw new ProductCollectionException(ProductCollectionException.ProductAlreadyExists());
				
			}else if(product.getName().isEmpty() || product.getDolar_price() < 0 || product.getReal_price() < 0) {
				throw new ProductCollectionException(ProductCollectionException.FieldEmpty());
			}else {
			
			Product productUpdate = productWithId.get();
			
	
			productUpdate.setName(product.getName());
			productUpdate.setDolar_price(product.getDolar_price());
			productUpdate.setReal_price(product.getReal_price());
			productUpdate.setQuantity(product.getQuantity());
			
			return productRepository.save(productUpdate);
			
			}
		}else {
			throw new ProductCollectionException(ProductCollectionException.NotFoundException(id));
		}
		
	}

	@Override
	public void deleteOneProduct(String id) throws ProductCollectionException{
		
		Optional<Product> productOptional = productRepository.findById(id);
		
		if(!productOptional.isPresent()) {
			throw new ProductCollectionException(ProductCollectionException.NotFoundException(id));
		}else {
			productRepository.deleteById(id);
		}

	}

}
