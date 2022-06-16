package com.taik.webshop.controllers;

import com.taik.webshop.dto.BucketDetailDto;
import com.taik.webshop.dto.BucketDto;
import com.taik.webshop.dto.ProductDto;
import com.taik.webshop.service.BucketService;
import com.taik.webshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class BucketController {

    private final BucketService bucketService;
    private final ProductService productService;

    public BucketController(BucketService bucketService, ProductService productService) {
        this.bucketService = bucketService;
        this.productService = productService;
    }

    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDto());
        } else {
            BucketDto bucketDto = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDto);
        }

        return "bucket";
    }

    @PostMapping("/bucket")
    public String commitBucket(Principal principal) {
        if (principal != null) {
            bucketService.commitBucketToOrder(principal.getName());
        }
        return "redirect:/bucket";
    }

    @PostMapping("/{id}/bucket/delete")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
//        List<ProductDto> listProducts = productService.getAll();
//        model.addAttribute("products", listProducts);
        productService.deleteFromUserBucketProduct(id, principal.getName());
        return "redirect:/bucket";
    }


}
