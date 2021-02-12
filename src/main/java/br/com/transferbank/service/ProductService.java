package br.com.transferbank.service;

import java.util.List;

import br.com.transferbank.entity.Product;
import br.com.transferbank.exception.ProductCollectionException;

public interface ProductService {
	
	public List<Product> listAllProducts(Integer page, Integer size) throws ProductCollectionException;
	
	public Product findProductByID(String id) throws ProductCollectionException;
	
	public Product createProduct (Product product) throws ProductCollectionException;
	
	public Product updateProduct(String Id, Product product) throws ProductCollectionException;
	
	public void deleteOneProduct(String id) throws ProductCollectionException;

}
