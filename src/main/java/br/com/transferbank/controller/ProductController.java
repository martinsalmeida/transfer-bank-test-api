package br.com.transferbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Constants.ConstantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.transferbank.entity.Product;
import br.com.transferbank.exception.ProductCollectionException;
import br.com.transferbank.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
		
	
	@GetMapping()
	public ResponseEntity<?> allProducts (
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "4") Integer size
			
			) throws ProductCollectionException{
		List<Product> products = productService.listAllProducts(page, size);
		return new ResponseEntity<>(products, HttpStatus.OK );
	}
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getProductById(@PathVariable(name = "id") String id){
		try {
			return new ResponseEntity<>(productService.findProductByID(id), HttpStatus.OK);
		} catch (ProductCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
		
	@PostMapping("/new-product")
	public ResponseEntity<?> createProduct(@RequestBody Product product){
		
		
		
		try {
			productService.createProduct(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (ConstantException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ProductCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	@PatchMapping("/product/{id}")
	public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody Product product){
		try {
			productService.updateProduct(id, product);
			return new ResponseEntity<>("Produto alterado com sucesso", HttpStatus.OK);			
		} catch (ConstantException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ProductCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") String id){
		try {
			productService.deleteOneProduct(id);
			return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
		} catch (ProductCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
