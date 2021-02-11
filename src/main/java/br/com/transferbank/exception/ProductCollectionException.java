package br.com.transferbank.exception;

public class ProductCollectionException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public ProductCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Produto não encontrado!";
	}
	
	public static String ProductAlreadyExists() {
		return "Já existe um produto com esse nome!";
	}
	
	public static String FieldEmpty() {
		return "O nome produto não pode estar vazio ou valor devem ser maior que 0";
	}
}
