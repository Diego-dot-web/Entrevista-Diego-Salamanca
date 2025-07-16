package com.entrevista.practice.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrevista.practice.controllers.ProductsController;
import com.entrevista.practice.models.Products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class ProductsService {
	@Autowired
	private ProductsController controller;

	@GetMapping("/all")
	public ResponseEntity<List<Products>> getAll() {
		return new ResponseEntity<>(controller.getAll(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		boolean deleted = controller.deleteById(id);
		if (deleted) {
			return new ResponseEntity<>("Eliminado Correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createProduct(@RequestBody Products p) {
		boolean created = controller.createProduct(p);
		if (created) {
			return new ResponseEntity<>("Producto Creado Correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("Producto no pudo se Creado", HttpStatus.CONFLICT);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<String> putMethodName(@PathVariable int id, @RequestBody Products p) {
		String updated = controller.updateProductById(id, p);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@GetMapping("/quantity")
	public ResponseEntity<Integer> getTotalQuantity() {
		int total = controller.totalQuantity(0, 0);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}

	@GetMapping("/nameless")
	public ResponseEntity<Optional<Products>> getNameLess() {
		Optional<Products> product = controller.getNamewithLess();

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/primenumber")
	public ResponseEntity<String> getPrimeNumber() {
		String result = controller.primeNumber();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
