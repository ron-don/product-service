package com.ronnytech.productservice.service;

import com.ronnytech.productservice.dto.ProductRequest;
import com.ronnytech.productservice.dto.ProductResponse;
import com.ronnytech.productservice.model.Product;
import com.ronnytech.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // At compile time it will create the needed constructor (dependency injection) automatically
@Slf4j // For logs

public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId()); // for logs

    }

    // returns a list of all products.
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // map each product into ProductResponse object
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
