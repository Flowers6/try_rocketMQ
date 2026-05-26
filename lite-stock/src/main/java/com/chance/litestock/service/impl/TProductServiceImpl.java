package com.chance.litestock.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chance.litestock.domain.dao.TProduct;
import com.chance.litestock.enums.OperateTypeEnum;
import com.chance.litestock.service.TProductService;
import com.chance.litestock.mapper.TProductMapper;
import com.chance.litestock.service.inventory.InventoryOperationProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 32166
* @description 针对表【t_product(商品表)】的数据库操作Service实现
* @createDate 2026-05-13 20:37:04
*/
@Service
@RequiredArgsConstructor
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct>
    implements TProductService{

    private final InventoryOperationProxy inventoryOperationProxy;

    @Override
    public TProduct querySingleProduct(String productName) {
        return baseMapper.selectOne(Wrappers.lambdaQuery(TProduct.class).eq(TProduct::getProductName, productName));
    }

    @Override
    public void freezeProduct(Long productId, Integer amount, Long orderId) {
        inventoryOperationProxy.execute(productId, amount, orderId, OperateTypeEnum.FREEZE);
    }

    @Override
    public void unfreezeProduct(Long productId, Integer amount, Long orderId) {
        inventoryOperationProxy.execute(productId, amount, orderId, OperateTypeEnum.RELEASE);
    }

    @Override
    public void deductProduct(Long productId, Integer amount, Long orderId) {
        inventoryOperationProxy.execute(productId, amount, orderId, OperateTypeEnum.DEDUCT);
    }


}




