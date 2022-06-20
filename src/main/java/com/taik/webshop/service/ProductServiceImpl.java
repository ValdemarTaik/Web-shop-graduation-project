package com.taik.webshop.service;

import com.taik.webshop.dao.ProductRepository;
import com.taik.webshop.domain.Bucket;
import com.taik.webshop.domain.Product;
import com.taik.webshop.domain.User;
import com.taik.webshop.dto.ProductDto;
import com.taik.webshop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;
    // private final SimpMessagingTemplate template;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService,
                              BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDto> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @javax.transaction.Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found. " + username);
        }

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }

    @Override
    @javax.transaction.Transactional
    public void deleteFromUserBucketProduct(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found. " + username);
        }
        Bucket bucket = user.getBucket();


        bucketService.deleteProducts(bucket, Collections.singletonList(productId));
    }


    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return ProductMapper.MAPPER.fromProduct(product);
    }


    @Override
    public List<ProductDto> getProductByCategory(Long id) {
        List<Product> productDtoList = productRepository.findAll();
        if (id == 1) {

            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("Protein")).collect(Collectors.toList());
        } else if (id == 2) {

            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("Creatin")).collect(Collectors.toList());
        } else if (id == 3) {
            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("BCAA")).collect(Collectors.toList());
        } else if (id == 4) {
            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("Vitamins")).collect(Collectors.toList());
        } else if (id == 5) {
            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("Fat burners")).collect(Collectors.toList());
        } else if (id == 6) {
            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("Shakers and bottles")).collect(Collectors.toList());
        } else if (id == 7) {
            productDtoList = productDtoList.stream()
                    .filter(product -> product.getCategory().equals("Accessories")).collect(Collectors.toList());
        }

        return mapper.fromProductList(productDtoList);
    }
}
