package com.taik.webshop.controllers;


//import com.taik.webshop.domain.Category;

import com.taik.webshop.dto.CategoriesDto;
import com.taik.webshop.dto.ProductDto;
//import com.taik.webshop.service.CategoriesService;
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
//    private final CategoriesService categoriesService;


    public ProductController(ProductService productService) {
        this.productService = productService;
        //   this.categoriesService = categoriesService;
    }

    @GetMapping
    public String list(Model model) {
        List<ProductDto> listProducts = productService.getAll();
//        List<CategoriesDto> listCategories = categoriesService.getAll();
        model.addAttribute("products", listProducts);
//        model.addAttribute("categories", listCategories);
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
//        Category category = new Category();
//        category.setTitle("Protein");
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(1L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }


    @GetMapping("/creatin")
    public String listByCreatin(Model model) {
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(2L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }

    @GetMapping("/bca")
    public String listByBca(Model model) {
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(3L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }

    @GetMapping("/vitamins")
    public String listByVitamins(Model model) {
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(4L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }

    @GetMapping("/fatBurners")
    public String listByFatBurners(Model model) {
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(5L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }

    @GetMapping("/shakersBottles")
    public String listByShakersBottles(Model model) {
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(6L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }

    @GetMapping("/accessories")
    public String listByaccessories(Model model) {
        List<ProductDto> listProductsByProtein = productService.getProductByCategory(7L);
        model.addAttribute("products", listProductsByProtein);
        return "products";
    }


}
