package com.jedeiah.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jedeiah.product.entity.Products;
import com.jedeiah.product.mapper.ProductsMapper;
import com.jedeiah.product.service.ProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jedeiah.product.vo.ProductsVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
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
    public void add(ProductsVo productsVo) {
        Products one = this.getOne(new LambdaQueryWrapper<Products>().eq(Products::getName, productsVo.getName()));
        Assert.isNull(one,"产品已经存在");
        Products products = new Products();
        products.setName(productsVo.getName());
        productsMapper.insert(products);
    }

    @Override
    public int modify(ProductsVo productsVo) {
        Products currentProducts = productsMapper.selectById(productsVo.getId());
        Assert.notNull(currentProducts,"产品不存在");
        currentProducts.setName(productsVo.getName());
        return productsMapper.updateById(currentProducts);
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


