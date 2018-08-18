package com.capgemini.storeserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.storeserver.beans.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
