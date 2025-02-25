package com.shop.application.dao;

import com.shop.application.domain.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductNr(long productNr);

    boolean existsByProductNr(long productNr);

    void deleteByProductNr(long productNr);

    boolean existsByName(String name);
}
