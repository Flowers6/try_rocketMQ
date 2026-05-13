package com.chance.litestock.service;

import com.chance.litestock.domain.dao.TProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 32166
* @description 针对表【t_product(商品表)】的数据库操作Service
* @createDate 2026-05-13 20:37:04
*/
public interface TProductService extends IService<TProduct> {

    TProduct querySingleProduct(String productName);

    void freezeProduct(String productName, Integer amount);

    void unfreezeProduct(String productName, Integer amount);

    void deductProduct(String productName, Integer amount);
}
