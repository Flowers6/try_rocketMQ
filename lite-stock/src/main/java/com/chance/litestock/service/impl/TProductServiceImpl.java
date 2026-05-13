package com.chance.litestock.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chance.litestock.domain.dao.TProduct;
import com.chance.litestock.mapper.TInventoryRecordMapper;
import com.chance.litestock.service.TProductService;
import com.chance.litestock.mapper.TProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 32166
* @description 针对表【t_product(商品表)】的数据库操作Service实现
* @createDate 2026-05-13 20:37:04
*/
@Service
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct>
    implements TProductService{

    private final TInventoryRecordMapper tInventoryRecordMapper;

    public TProductServiceImpl(TInventoryRecordMapper tInventoryRecordMapper) {
        this.tInventoryRecordMapper = tInventoryRecordMapper;
    }

    @Override
    public TProduct querySingleProduct(String productName) {
        return baseMapper.selectOne(Wrappers.lambdaQuery(TProduct.class).eq(TProduct::getProductName, productName));
    }

    @Override
    public void freezeProduct(String productName, Integer amount) {
        baseMapper.update(Wrappers.lambdaUpdate(TProduct.class)
                .eq(TProduct::getProductName, productName)
                .setIncrBy(TProduct::getFrozenStock, amount)
                .setDecrBy(TProduct::getAvailableStock, amount));
    }

    @Override
    public void unfreezeProduct(String productName, Integer amount) {
        baseMapper.update(Wrappers.lambdaUpdate(TProduct.class)
                .eq(TProduct::getProductName, productName)
                .setDecrBy(TProduct::getFrozenStock, amount)
                .setIncrBy(TProduct::getAvailableStock, amount));
    }

    @Override
    public void deductProduct(String productName, Integer amount) {
        baseMapper.update(Wrappers.lambdaUpdate(TProduct.class)
                .eq(TProduct::getProductName, productName)
                .setDecrBy(TProduct::getFrozenStock, amount));
    }


}




