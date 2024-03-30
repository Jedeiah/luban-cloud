package com.jedeiah.product.service;

import com.jedeiah.product.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
public interface ProductsService extends IService<Products> {

    /**
     * Products详情
     *
     * @param
     * @return
     */
    Products getProducts(Integer id);

    /**
     * Products详情
     *
     * @param
     * @return
     */
    List<Products> getAllProducts();

    /**
     * Products新增
     *
     * @param products 根据需要进行传值
     * @return
     */
    void add(Products products);

    /**
     * Products修改
     *
     * @param products 根据需要进行传值
     * @return
     */
    int modify(Products products);

    /**
     * Products删除
     *
     * @param ids
     * @return
     */
    void remove(String ids);
}


