package org.example.service;

import org.example.domain.Product;
import org.example.dto.ProductDTO;

public interface ProductService {

    Product create(ProductDTO productDTO);

    ProductDTO findByName(String name);
}
