package com.chance.litestock.enums;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.chance.litestock.domain.dao.TProduct;
import lombok.Getter;

@Getter
public enum OperateTypeEnum {

    FREEZE("冻结库存") {
        @Override
        public LambdaUpdateWrapper<TProduct> buildUpdateWrapper(String productName, Integer amount) {
            return new LambdaUpdateWrapper<TProduct>()
                    .eq(TProduct::getProductName, productName)
                    .setIncrBy(TProduct::getFrozenStock, amount)
                    .setDecrBy(TProduct::getAvailableStock, amount);
        }
    },

    RELEASE("释放库存") {
        @Override
        public LambdaUpdateWrapper<TProduct> buildUpdateWrapper(String productName, Integer amount) {
            return new LambdaUpdateWrapper<TProduct>()
                    .eq(TProduct::getProductName, productName)
                    .setDecrBy(TProduct::getFrozenStock, amount)
                    .setIncrBy(TProduct::getAvailableStock, amount);
        }
    },

    DEDUCT("正式扣减库存") {
        @Override
        public LambdaUpdateWrapper<TProduct> buildUpdateWrapper(String productName, Integer amount) {
            return new LambdaUpdateWrapper<TProduct>()
                    .eq(TProduct::getProductName, productName)
                    .setDecrBy(TProduct::getFrozenStock, amount);
        }
    };

    private final String description;

    OperateTypeEnum(String description) {
        this.description = description;
    }

    public abstract LambdaUpdateWrapper<TProduct> buildUpdateWrapper(String productName, Integer amount);

}
