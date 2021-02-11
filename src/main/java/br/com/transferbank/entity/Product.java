package br.com.transferbank.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

	@Id
	private String id;
	
	@NotNull(message = "O nome é obrigatório") 
	private String name;
	@NotNull(message = "O preço é obrigatório")
	private Double dolar_price;
	@NotNull(message = "O preço é obrigatório")
	private Double real_price;
	@NotNull(message = "O quantidade é obrigatória")
	private Integer quantity;
	private LocalDateTime create_at;
	
	
	public Product() {}


	public Product(String id, String name, Double dolar_price, Double real_price, Integer quantity,
			LocalDateTime create_at) {
		super();
		this.id = id;
		this.name = name;
		this.dolar_price = dolar_price;
		this.real_price = real_price;
		this.quantity = quantity;
		this.create_at = create_at;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getDolar_price() {
		return dolar_price;
	}


	public void setDolar_price(Double dolar_price) {
		this.dolar_price = dolar_price;
	}


	public Double getReal_price() {
		return real_price;
	}


	public void setReal_price(Double real_price) {
		this.real_price = real_price;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public LocalDateTime getCreate_at() {
		return create_at;
	}


	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}
	
	
	
	
	
}
