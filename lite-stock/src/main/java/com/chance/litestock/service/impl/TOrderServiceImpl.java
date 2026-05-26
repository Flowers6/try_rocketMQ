package com.chance.litestock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chance.litestock.domain.dao.TOrder;
import com.chance.litestock.domain.dao.TOrderItem;
import com.chance.litestock.domain.dto.CreateOrder;
import com.chance.litestock.domain.dto.CreateOrderItem;
import com.chance.litestock.mapper.TOrderItemMapper;
import com.chance.litestock.service.TOrderService;
import com.chance.litestock.mapper.TOrderMapper;
import com.chance.litestock.service.TProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 32166
* @description 针对表【t_order(订单表)】的数据库操作Service实现
* @createDate 2026-05-13 20:37:04
*/
@Service
@RequiredArgsConstructor
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder>
    implements TOrderService{

    private final TOrderItemMapper tOrderItemMapper;

    private final TProductService tProductService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrder createOrder) {
        // 创建订单
        TOrder tOrder = buildOrder(createOrder.getItems());
        baseMapper.insert(tOrder);

        // 创建订单明细
        List<TOrderItem> tOrderItems = buildOrderItems(tOrder.getId(), createOrder.getItems());
        tOrderItemMapper.insert(tOrderItems);

        // 库存处理
        tOrderItems.forEach(tOrderItem -> {
            tProductService.freezeProduct(tOrderItem.getProductId(), tOrderItem.getQuantity(), tOrder.getId());
        });

        // TODO 发送延迟消息
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pay(Long orderId) {
        // 校验订单状态
        // 修改订单状态
        // 扣减冻结库存
        // 记录库存流水
    }

    /**
     * 构建订单明细
     * @param orderId           订单ID
     * @param items             创建订单项参数
     * @return List<TOrderItem> 订单明细
     */
    private List<TOrderItem> buildOrderItems(Long orderId, List<CreateOrderItem> items) {
        return items.stream().map(item -> {
            TOrderItem tOrderItem = new TOrderItem();
            tOrderItem.createOrderItem(orderId, item.getProductId(), item.getQuantity());
            return tOrderItem;
        }).toList();
    }

    /**
     * 构建订单
     * @param items
     * @return TOrder
     */
    private TOrder buildOrder(List<CreateOrderItem> items) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        items.forEach(item -> {
            // TODO orderItem have no price in current version
            totalAmount.add(BigDecimal.ZERO);
        });
        TOrder tOrder = new TOrder();
        tOrder.createOrder(totalAmount);
        return tOrder;
    }
}




