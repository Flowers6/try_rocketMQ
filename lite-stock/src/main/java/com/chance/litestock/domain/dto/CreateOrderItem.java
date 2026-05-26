package com.chance.litestock.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description : 创建订单项参数
 * @date : 2026/5/26
 * @time : 10:21
 */
@Data
public class CreateOrderItem implements Serializable {

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;

    /**
     * 购买数量
     */
    @Schema(description = "购买数量")
    private Integer quantity;

}
