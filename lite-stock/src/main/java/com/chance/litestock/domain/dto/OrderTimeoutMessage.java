package com.chance.litestock.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderTimeoutMessage {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 创建时间
     */
    private Date createTime;

    public static OrderTimeoutMessage build(Long orderId, String orderNo) {
        OrderTimeoutMessage message = new OrderTimeoutMessage();
        message.setOrderId(orderId);
        message.setOrderNo(orderNo);
        message.setCreateTime(new Date());
        return message;
    }
}