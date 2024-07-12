package org.example.service;

import org.example.domain.Product;
import org.example.dto.CouponDTO;
import org.example.dto.ProductDTO;
import org.example.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    //private final RestTemplate restTemplate;
    private final RestClient.Builder restClientBuilder;

    public ProductServiceImpl(ProductRepository productRepository, /*RestTemplate restTemplate,*/ RestClient.Builder restClientBuilder) {
        this.productRepository = productRepository;
        //this.restTemplate = restTemplate;
        this.restClientBuilder = restClientBuilder;
    }

    @Override
    public Product create(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        /*CouponDTO couponDTO = restTemplate.getForObject("http://discount/api/v1/coupon/{code}",
                CouponDTO.class,
                productDTO.getDiscountCode());*/
        CouponDTO couponDTO = restClientBuilder.build().get()
                .uri("http://DISCOUNT/api/v1/coupon/{code}", productDTO.getDiscountCode())
                .retrieve()
                .body(CouponDTO.class);
        BigDecimal subtract = new BigDecimal("100").subtract(couponDTO.getDiscount());
        product.setPrice(subtract.multiply(productDTO.getPrice()).divide(new BigDecimal("100")));
        productDTO.setPrice(product.getPrice());
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
