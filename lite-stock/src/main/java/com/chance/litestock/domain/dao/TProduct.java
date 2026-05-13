package com.chance.litestock.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 商品表
 * @TableName t_product
 */
@TableName(value ="t_product")
@Data
public class TProduct {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 可用库存
     */
    @TableField(value = "available_stock")
    private Integer availableStock;

    /**
     * 冻结库存
     */
    @TableField(value = "frozen_stock")
    private Integer frozenStock;

    /**
     * 乐观锁版本号
     */
    @TableField(value = "version")
    private Integer version;

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