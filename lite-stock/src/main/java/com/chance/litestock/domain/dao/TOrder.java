package com.chance.litestock.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

import com.chance.litestock.enums.OrderStatusEnum;
import lombok.Data;

/**
 * 订单表
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
public class TOrder {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private OrderStatusEnum orderStatus;

    /**
     * 订单总金额
     */
    @TableField(value = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}