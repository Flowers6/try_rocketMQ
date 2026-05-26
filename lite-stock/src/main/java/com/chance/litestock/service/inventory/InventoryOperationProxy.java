package com.chance.litestock.service.inventory;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chance.litestock.domain.dao.TInventoryRecord;
import com.chance.litestock.domain.dao.TProduct;
import com.chance.litestock.enums.OperateTypeEnum;
import com.chance.litestock.mapper.TInventoryRecordMapper;
import com.chance.litestock.mapper.TProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InventoryOperationProxy {

    private final TProductMapper tProductMapper;

    private final TInventoryRecordMapper tInventoryRecordMapper;

    public int execute(Long productId, Integer amount, Long orderId, OperateTypeEnum operateType) {
        List<TProduct> tProducts = tProductMapper.selectList(
                Wrappers.lambdaQuery(TProduct.class).eq(TProduct::getId, productId)
        );

        int effectRow = tProductMapper.update(operateType.buildUpdateWrapper(productId, amount));

        List<TInventoryRecord> records = tProducts.stream()
                .filter(ObjectUtil::isNotNull)
                .map(tProduct -> new TInventoryRecord.TInventoryRecordBuilder()
                        .orderId(orderId)
                        .productId(tProduct.getId())
                        .operateType(operateType)
                        .quantity(amount)
                        .remark(operateType.getDescription())
                        .build())
                .toList();

        if (!records.isEmpty()) {
            tInventoryRecordMapper.insert(records);
        }

        return effectRow;
    }
}
