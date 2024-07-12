package org.example.controller;

import org.example.domain.Product;
import org.example.dto.ProductDTO;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        productService.create(productDTO);
        return productDTO;
    }

    @GetMapping("/{name}")
    public ProductDTO findByName(@PathVariable("name") String name) {
        return productService.findByName(name);
    }
}
