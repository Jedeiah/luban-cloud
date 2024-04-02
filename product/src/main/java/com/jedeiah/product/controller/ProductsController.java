package com.jedeiah.product.controller;

import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.commons.group.AddGroup;
import com.jedeiah.commons.group.UpdateGroup;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.product.aop.PermissionRequired;
import com.jedeiah.product.entity.Products;
import com.jedeiah.product.service.ProductsService;
import com.jedeiah.product.vo.ProductsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public RespVo<Products> getProducts(@RequestParam("id") Integer id) {
        Products productsOne = productsService.getProducts(id);
        return RespVo.success(productsOne);
    }

    @GetMapping("/listAll")
    @Operation(summary = "Products查询全部")
    @PermissionRequired(operation = PermissionEnum.READ)
    public RespVo<List<Products>> getAllProducts() {
        List<Products> productsList = productsService.getAllProducts();
        return RespVo.success(productsList);
    }

    @PostMapping("/add")
    @Operation(summary = "Products新增")
    @PermissionRequired(operation = PermissionEnum.CREATE)
    public RespVo add(@Validated(AddGroup.class) @RequestBody ProductsVo productsVo) {
        productsService.add(productsVo);
        return RespVo.success("新增成功");
    }

    @PutMapping("/update")
    @Operation(summary = "Products修改")
    @PermissionRequired(operation = PermissionEnum.UPDATE)
    public RespVo update(@Validated(UpdateGroup.class) @RequestBody ProductsVo productsVo) {
        int num = productsService.modify(productsVo);
        return RespVo.success("修改成功");
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Products删除(单个条目)")
    @PermissionRequired(operation = PermissionEnum.DELETE)
    public RespVo remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        productsService.remove(ids);
        return RespVo.success("删除成功");
    }
}
