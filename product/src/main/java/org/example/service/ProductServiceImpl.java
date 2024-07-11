package org.example.service;

import org.example.domain.Product;
import org.example.dto.ProductDTO;
import org.example.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return productRepository.save(product);
    }

    @Override
    public ProductDTO findByName(String name) {
        Optional<Product> optionalProduct = productRepository.findByName(name);
        if (optionalProduct.isEmpty())
            throw new RuntimeException("not found");
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(optionalProduct.get(), productDTO);
        return productDTO;
    }
}
