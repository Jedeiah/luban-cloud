package com.jedeiah.product.controller;

import com.jedeiah.product.entity.Products;
import com.jedeiah.product.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/products")
@Tag(name = "")
public class ProductsController {

    @Autowired
    private ProductsService productsService;


    @GetMapping("/selectOne")
    @Operation(summary = "Products查询单个")
    public Products getProducts(@RequestParam("id") Integer id) {
        Products productsOne = productsService.getProducts(id);
        return productsOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "Products查询全部")
    public List<Products> getAllProducts() {
        List<Products> productsList = productsService.getAllProducts();
        return productsList;
    }

    @PostMapping("/add")
    @Operation(summary = "Products新增")
    public Object add(@Valid @RequestBody Products products) {
        productsService.add(products);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "Products修改")
    public int update(@Valid @RequestBody Products products) {
        int num = productsService.modify(products);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Products删除(单个条目)")
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        productsService.remove(ids);
        return null;
    }
}
