package com.jedeiah.product.controller;

import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.product.aop.PermissionRequired;
import com.jedeiah.product.entity.Products;
import com.jedeiah.product.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/products")
@Tag(name = "产品接口")
public class ProductsController {

    @Autowired
    private ProductsService productsService;


    @GetMapping("/selectOne")
    @Operation(summary = "Products查询单个")
    @PermissionRequired(operation = PermissionEnum.READ)
    public Products getProducts(@RequestParam("id") Integer id) {
        Products productsOne = productsService.getProducts(id);
        return productsOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "Products查询全部")
    @PermissionRequired(operation = PermissionEnum.READ)
    public List<Products> getAllProducts() {
        List<Products> productsList = productsService.getAllProducts();
        return productsList;
    }

    @PostMapping("/add")
    @Operation(summary = "Products新增")
    @PermissionRequired(operation = PermissionEnum.CREATE)
    public Object add(@Valid @RequestBody Products products) {
        productsService.add(products);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "Products修改")
    @PermissionRequired(operation = PermissionEnum.UPDATE)
    public int update(@Valid @RequestBody Products products) {
        int num = productsService.modify(products);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Products删除(单个条目)")
    @PermissionRequired(operation = PermissionEnum.DELETE)
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        productsService.remove(ids);
        return null;
    }
}
