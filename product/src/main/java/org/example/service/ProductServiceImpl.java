package org.example.service;

import org.example.discount.CouponDTO;
import org.example.discount.DiscountClient;
import org.example.domain.Product;
import org.example.dto.ProductDTO;
import org.example.notification.NotificationClient;
import org.example.notification.NotificationDTO;
import org.example.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    //private final RestTemplate restTemplate;
   // private final RestClient.Builder restClientBuilder;
    private final DiscountClient discountClient;
    private final NotificationClient notificationClient;

    public ProductServiceImpl(ProductRepository productRepository, DiscountClient discountClient, NotificationClient notificationClient /*RestTemplate restTemplate, RestClient.Builder restClientBuilder*/) {
        this.productRepository = productRepository;
        //this.restTemplate = restTemplate;
        //this.restClientBuilder = restClientBuilder;
        this.discountClient = discountClient;
        this.notificationClient = notificationClient;
    }

    @Override
    public Product create(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        /*CouponDTO couponDTO = restTemplate.getForObject("http://discount/api/v1/coupon/{code}",
                CouponDTO.class,
                productDTO.getDiscountCode());*/
        /*CouponDTO couponDTO = restClientBuilder.build().get()
                .uri("http://DISCOUNT/api/v1/coupon/{code}", productDTO.getDiscountCode())
                .retrieve()
                .body(CouponDTO.class);*/
        CouponDTO couponDTO = discountClient.findByCouponCode(product.getDiscountCode());
        BigDecimal subtract = new BigDecimal("100").subtract(couponDTO.getDiscount());
        product.setPrice(subtract.multiply(productDTO.getPrice()).divide(new BigDecimal("100")));
        productDTO.setPrice(product.getPrice());
        productRepository.save(product);
        notificationClient.sendNotification(createNotification());
        return product;
    }

    private NotificationDTO createNotification() {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setCallerService("product");
        notificationDTO.setMessage("product saved");
        notificationDTO.setDate(LocalDate.now());
        return notificationDTO;
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
