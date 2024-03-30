package com.jedeiah.product.service.impl;

import com.jedeiah.product.entity.Products;
import com.jedeiah.product.mapper.ProductsMapper;
import com.jedeiah.product.service.ProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Arrays;

/**
 * 服务实现类
 *
 * @author chj
 * @since 2024-03-30
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

    @Autowired
    ProductsMapper productsMapper;


    @Override
    public Products getProducts(Integer id) {

        return productsMapper.selectById(id);
    }

    @Override
    public List<Products> getAllProducts() {
        return productsMapper.selectList(null);

    }

    @Override
    public void add(Products products) {
        productsMapper.insert(products);
    }

    @Override
    public int modify(Products products) {
        //乐观锁更新
        Products currentProducts = productsMapper.selectById(products.getId());
        return productsMapper.updateById(products);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                productsMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

}


