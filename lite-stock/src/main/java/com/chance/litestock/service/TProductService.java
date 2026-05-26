package com.chance.litestock.service;

import com.chance.litestock.domain.dao.TProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 32166
* @description 针对表【t_product(商品表)】的数据库操作Service
* @createDate 2026-05-13 20:37:04
*/
public interface TProductService extends IService<TProduct> {

    /**
     * 查询单个商品
     *
     * @param productName 商品名称
     * @return 商品
     */
    TProduct querySingleProduct(String productName);

    /**
     * 冻结商品
     *
     * @param productId 商品ID
     * @param amount    冻结数量
     * @param orderId   订单ID
     * @return 影响行数
     */
    int freezeProduct(Long productId, Integer amount, Long orderId);

    /**
     * 释放商品
     *
     * @param productId 商品ID
     * @param amount    解冻数量
     * @param orderId   订单ID
     * @return 影响行数
     */
    int unfreezeProduct(Long productId, Integer amount, Long orderId);

    /**
     * 扣减商品
     *
     * @param productId 商品ID
     * @param amount    扣减数量
     * @param orderId   订单ID
     * @return 影响行数
     */
    int deductProduct(Long productId, Integer amount, Long orderId);
}
