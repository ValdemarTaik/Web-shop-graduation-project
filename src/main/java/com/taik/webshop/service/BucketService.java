package com.taik.webshop.service;

import com.taik.webshop.domain.Bucket;
import com.taik.webshop.domain.User;
import com.taik.webshop.dto.BucketDetailDto;
import com.taik.webshop.dto.BucketDto;
import com.taik.webshop.dto.ProductDto;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    void deleteProducts(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name);

    void commitBucketToOrder(String username);
}


