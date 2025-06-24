package com.entrevista.practice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entrevista.practice.models.Products;
import com.entrevista.practice.repositories.ProductsRepository;

@Component
public class ProductsController {
	@Autowired
	private ProductsRepository repository;

	// Read
	public List<Products> getAll() {
		List<Products> products = (List<Products>) repository.findAll();
		return products;
	}

	// Delete
	public boolean deleteById(int id) {
		Optional<Products> product = repository.findById(id);
		if (product.isPresent()) {
			repository.deleteById(id);
			return true;
		}

		return false;

	}

	// Create
	public boolean createProduct(Products receiveProduct) {
		try {
			repository.save(receiveProduct);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Update
	public String updateProductById(int id, Products product) {

		Optional<Products> existProduct = repository.findById(id);
		try {
			if (existProduct.isPresent()) {
				existProduct.get().setName(product.getName());
				existProduct.get().setDescription(product.getDescription());
				existProduct.get().setPrice(product.getPrice());
				existProduct.get().setQuantity(product.getQuantity());
			}
			return "Producto Actualizado" + existProduct.get();
		} catch (Exception e) {
			return "No existe ese producto";
		}

	}

	public int totalQuantity(int index, int allp) {
		List<Products> products = getAll();

		if (index >= products.size())
			return allp;

		Products actual = products.get(index);
		allp += actual.getQuantity();

		return totalQuantity(index + 1, allp);
	}

	public Optional<Products> getNamewithLess() {
		List<Products> products = (List<Products>) repository.findAll();
		Products finalProduct = Products.builder().name("a").price(0).quantity(0).build();

		for (Products p : products) {
			// reemplazo vocales, numeros, y simbolos.
			String namep = p.getName().replaceAll(" ", "").replaceAll("aeiou1234567890.-,?", "");
			if (namep.length() > finalProduct.getName().length())
				finalProduct = p;
		}

		return repository.findById(finalProduct.getId());
	}

	public String primeNumber() {
		List<Products> products = getAll();
		int pricelowest = products.get(0).getPrice();
		var prime = 0;

		for (Products p : products) {
			if (p.getPrice() < pricelowest)
				pricelowest = p.getPrice();
		}

		prime = findNearestPrime(pricelowest);
		return "El numero primo más cercano a el precio más bajo es: " + prime;

	}

	private int findNearestPrime(int number) {
		int lower = number;
		int upper = number;

		while (true) {
			if (isPrime(lower))
				return lower;
			if (isPrime(upper))
				return upper;
			lower--;
			upper++;
		}
	}

	private boolean isPrime(int num) {
		if (num < 2)
			return false;

		// todos los divisores se combinan con los anteriores
		// al dividir el numero se evaluan sus divisores sin tantas iteraciones
		// si da 1 no es primo.
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}
