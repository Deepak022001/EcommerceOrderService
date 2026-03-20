package com.example.OrderService.Client;

import com.example.OrderService.dto.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Component
public class ProductServiceClient {
    private final RestTemplate restTemplate;
    private final String productServiceBaseUrl;

    public ProductServiceClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${product.service.base-url}") String productServiceBaseUrl
    ){
        this.restTemplate=restTemplateBuilder.build();
        this.productServiceBaseUrl = productServiceBaseUrl;
    }

    public ProductDTO getProductById(Long productId){
        // Use a configurable base URL and the correct REST path format for product lookups.
        String url = productServiceBaseUrl + "/api/products/" + productId;
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
        ProductDTO product = response.getBody();

        if (product == null) {
            // Fail fast with a clear upstream error instead of propagating a null into order creation.
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Product service returned an empty response for product " + productId
            );
        }

        return product;
    }
}
