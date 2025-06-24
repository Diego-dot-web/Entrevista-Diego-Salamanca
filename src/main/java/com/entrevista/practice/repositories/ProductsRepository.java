package com.entrevista.practice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.entrevista.practice.models.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
