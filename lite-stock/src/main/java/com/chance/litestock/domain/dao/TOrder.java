package com.chance.litestock.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    /**
     * 创建订单
     */
    public void createOrder(BigDecimal totalAmount) {
        this.generateOrderNo();
        this.totalAmount = totalAmount;
        orderStatus = OrderStatusEnum.WAIT_PAY;
        createTime = new Date();
    }

    /**
     * 生成订单号
     * 规则：ORD + yyyyMMddHHmmss + 4位随机数
     * 例如：ORD202605261430251234
     */
    public void generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        int randomNum = (int) (Math.random() * 9000) + 1000; // 生成1000-9999的随机数
        this.orderNo = "ORD" + timestamp + randomNum;
    }
}