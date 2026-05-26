package com.chance.litestock.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description : 创建订单参数
 * @date : 2026/5/26
 * @time : 10:20
 */
@Data
public class CreateOrder implements Serializable {

    private List<CreateOrderItem> items;

}
