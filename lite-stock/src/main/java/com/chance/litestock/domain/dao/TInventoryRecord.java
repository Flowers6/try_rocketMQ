package com.chance.litestock.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.chance.litestock.enums.OperateTypeEnum;
import lombok.Data;

/**
 * 库存流水表
 * @TableName t_inventory_record
 */
@TableName(value ="t_inventory_record")
@Data
public class TInventoryRecord {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 商品ID
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 库存操作类型
     */
    @TableField(value = "operate_type")
    private OperateTypeEnum operateType;

    /**
     * 操作数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    private TInventoryRecord(TInventoryRecordBuilder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.productId = builder.productId;
        this.operateType = builder.operateType;
        this.quantity = builder.quantity;
        this.remark = builder.remark;
        this.createTime = builder.createTime;
    }

    public static class TInventoryRecordBuilder {

        private Long id;
        private Long orderId;
        private Long productId;
        private OperateTypeEnum operateType;
        private Integer quantity;
        private String remark;
        private Date createTime;
        public TInventoryRecordBuilder() {
        }
        public TInventoryRecordBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public TInventoryRecordBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }
        public TInventoryRecordBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }
        public TInventoryRecordBuilder operateType(OperateTypeEnum operateType) {
            this.operateType = operateType;
            return this;
        }
        public TInventoryRecordBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }
        public TInventoryRecordBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }
        public TInventoryRecordBuilder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
        public TInventoryRecord build() {
            return new TInventoryRecord(this);
        }
        public TInventoryRecordBuilder(TInventoryRecord tInventoryRecord) {
            this.id = tInventoryRecord.id;
            this.orderId = tInventoryRecord.orderId;
            this.productId = tInventoryRecord.productId;
            this.operateType = tInventoryRecord.operateType;
            this.quantity = tInventoryRecord.quantity;
            this.remark = tInventoryRecord.remark;
            this.createTime = tInventoryRecord.createTime;
        }
        public static TInventoryRecordBuilder builder() {
            return new TInventoryRecordBuilder();
        }
        public static TInventoryRecordBuilder builder(TInventoryRecord tInventoryRecord) {
            return new TInventoryRecordBuilder(tInventoryRecord);
        }
    }
}