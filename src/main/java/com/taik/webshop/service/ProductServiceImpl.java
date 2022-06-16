package com.taik.webshop.service;

import com.taik.webshop.dao.ProductRepository;
import com.taik.webshop.domain.Bucket;
import com.taik.webshop.domain.Category;
import com.taik.webshop.domain.Product;
import com.taik.webshop.domain.User;
import com.taik.webshop.dto.ProductDto;
import com.taik.webshop.mapper.ProductMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

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
        if(user == null){
            throw new RuntimeException("User not found. " + username);
        }

        Bucket bucket = user.getBucket();
        if(bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        }
        else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }

    @Override
    @javax.transaction.Transactional
    public void deleteFromUserBucketProduct(Long productId, String username) {
        User user = userService.findByName(username);
        if(user == null){
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

    // ------------------------------------------------------------------------------------
    @Override
    public List<ProductDto> getProductByCategory(Category category) {
        return mapper.fromProductList(productRepository.findAll());
    }


}
