package com.chance.litestock.consumer;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chance.litestock.domain.dao.TOrder;
import com.chance.litestock.domain.dao.TOrderItem;
import com.chance.litestock.domain.dto.OrderTimeoutMessage;
import com.chance.litestock.enums.OrderStatusEnum;
import com.chance.litestock.mapper.TOrderItemMapper;
import com.chance.litestock.mapper.TOrderMapper;
import com.chance.litestock.service.TProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.annotation.RocketMQMessageListener;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.chance.litestock.consts.MQConst.ORDER_TIMEOUT_CONSUMER;
import static com.chance.litestock.consts.MQConst.ORDER_TIMEOUT_TOPIC;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description : 订单超时消费者
 * @date : 2026/5/26
 * @time : 15:58
 */
@Component
@RocketMQMessageListener(topic = ORDER_TIMEOUT_TOPIC, consumerGroup = ORDER_TIMEOUT_CONSUMER)
@Slf4j
@RequiredArgsConstructor
public class OrderTimeoutConsumer implements RocketMQListener {

    private final TOrderMapper tOrderMapper;

    private final TOrderItemMapper tOrderItemMapper;

    private final TProductService tProductService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConsumeResult consume(MessageView messageView) {
        // 解析消息
        OrderTimeoutMessage message = parseMessage(messageView);

        // 查询订单状态
        validateOrderStatus(message.getOrderId());

        // 关闭订单
        closeOrder(message.getOrderId());

        // 释放库存，记录库存流水
        releaseStock(message.getOrderId());

        return ConsumeResult.SUCCESS;
    }

    /**
     * 解析消息
     * @param messageView 消息视图
     * @return OrderTimeoutMessage 超时订单消息
     */
    private OrderTimeoutMessage parseMessage(MessageView messageView) {
        try {
            String body = new String(messageView.getBody().array(), StandardCharsets.UTF_8);
            return objectMapper.readValue(body, OrderTimeoutMessage.class);
        } catch (Exception e) {
            log.error("解析订单超时消息失败", e);
            throw new RuntimeException("解析订单超时消息失败", e);
        }
    }

    /**
     * 校验订单状态
     * @param orderId 订单ID
     */
    private void validateOrderStatus(Long orderId) {
        TOrder order = tOrderMapper.selectById(orderId);
        if (order == null) {
            log.warn("订单不存在，orderId: {}", orderId);
            throw new RuntimeException("订单不存在");
        }

        if (!OrderStatusEnum.WAIT_PAY.equals(order.getOrderStatus())) {
            log.info("订单状态不是待支付状态，无需处理，orderId: {}, status: {}",
                    orderId, order.getOrderStatus());
            throw new RuntimeException("订单状态异常，无需处理");
        }
    }

    /**
     * 关闭订单
     * @param orderId 订单ID
     */
    private void closeOrder(Long orderId) {
        tOrderMapper.updateOrderStatus(orderId, OrderStatusEnum.CLOSED);
        log.info("订单已关闭，orderId: {}", orderId);
    }

    /**
     * 释放库存，记录库存流水
     * @param orderId 订单ID
     */
    private void releaseStock(Long orderId) {
        List<TOrderItem> orderItems = tOrderItemMapper.selectList(
                Wrappers.lambdaQuery(TOrderItem.class)
                        .eq(TOrderItem::getOrderId, orderId)
        );

        for (TOrderItem item : orderItems) {
            tProductService.unfreezeProduct(item.getProductId(), item.getQuantity(), orderId);
            log.info("释放库存成功，productId: {}, quantity: {}, orderId: {}",
                    item.getProductId(), item.getQuantity(), orderId);
        }
    }

}
