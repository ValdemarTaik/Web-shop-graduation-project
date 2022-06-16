package com.taik.webshop.controllers;


import com.taik.webshop.dto.CategoriesDto;
import com.taik.webshop.dto.ProductDto;
import com.taik.webshop.service.CategoriesService;
import com.taik.webshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoriesService categoriesService;


    public ProductController(ProductService productService, CategoriesService categoriesService) {
        this.productService = productService;
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public String list(Model model) {
        List<ProductDto> listProducts = productService.getAll();
        List<CategoriesDto> listCategories = categoriesService.getAll();
        model.addAttribute("products", listProducts);
        model.addAttribute("categories", listCategories);
        return "products";
    }

    //пас параметр
    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }



    //new
//    @PostMapping("/{id}/bucket")
//    public String  deleteProduct(@PathVariable Long id, Principal principal, Model model){
//        List<ProductDto> listProducts = productService.getAll();
//        model.addAttribute("products", listProducts);
//        productService.deleteFromUserBucketProduct(id, principal.getName());
//        return "redirect:/bucket";
//    }


    @GetMapping("/{id}")
    @ResponseBody
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    //вывод под категории
    @GetMapping("/protein")
    public String listByProtein(Model model) {
        Long SQL = Long.valueOf("SELECT product_id from products_categories WHERE category_id = '1' ");


//        List<ProductDto> listProducts = productService.getById();
//        for (ProductDto p : listProducts) {
//        if ( p.getId())
//        }

//        List<CategoriesDto> listCategories = categoriesService.getAll();
//        model.addAttribute("products", listProducts);

        return "products/protein";
    }

    @GetMapping("/creatin")
    public String listByCreatin(Model model) {
        Long SQL = Long.valueOf("SELECT product_id from products_categories WHERE category_id = '2' ");


        return "products/creatin";
    }

    @GetMapping("/bca")
    public String listByBca(Model model) {
        Long SQL = Long.valueOf("SELECT product_id from products_categories WHERE category_id = '3' ");


        return "products/bca";
    }


}
