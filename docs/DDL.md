# DDL

---

# 1. 商品表

## t_product

```sql
DROP TABLE IF EXISTS t_product;

CREATE TABLE t_product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    product_name VARCHAR(100) NOT NULL COMMENT '商品名称',

    available_stock INT NOT NULL DEFAULT 0 COMMENT '可用库存',

    frozen_stock INT NOT NULL DEFAULT 0 COMMENT '冻结库存',

    version INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',

    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='商品表';
```

---

# 2. 订单表

## t_order

```sql
DROP TABLE IF EXISTS t_order;

CREATE TABLE t_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    order_no VARCHAR(64) NOT NULL UNIQUE COMMENT '订单号',

    order_status VARCHAR(32) NOT NULL COMMENT '订单状态',

    total_amount DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '订单总金额',

    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='订单表';
```

---

# 3. 订单明细表

## t_order_item

```sql
DROP TABLE IF EXISTS t_order_item;

CREATE TABLE t_order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    order_id BIGINT NOT NULL COMMENT '订单ID',

    product_id BIGINT NOT NULL COMMENT '商品ID',

    quantity INT NOT NULL COMMENT '购买数量',

    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='订单明细表';
```

---

# 4. 库存流水表（重点）

## t_inventory_record

```sql
DROP TABLE IF EXISTS t_inventory_record;

CREATE TABLE t_inventory_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    order_id BIGINT NOT NULL COMMENT '订单ID',

    product_id BIGINT NOT NULL COMMENT '商品ID',

    operate_type VARCHAR(32) NOT NULL COMMENT '库存操作类型',

    quantity INT NOT NULL COMMENT '操作数量',

    remark VARCHAR(255) DEFAULT NULL COMMENT '备注',

    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='库存流水表';
```

---

# 5. 初始化测试数据

## 商品初始化

```sql
INSERT INTO t_product
(product_name, available_stock, frozen_stock)
VALUES
('MacBook Pro', 100, 0);

INSERT INTO t_product
(product_name, available_stock, frozen_stock)
VALUES
('iPhone 16', 200, 0);

INSERT INTO t_product
(product_name, available_stock, frozen_stock)
VALUES
('AirPods Pro', 300, 0);
```

---

# 6. 状态枚举建议

## 订单状态

| 状态 | 含义 |
|---|---|
| WAIT_PAY | 待支付 |
| PAID | 已支付 |
| CLOSED | 已关闭 |

---

## 库存流水类型

| 类型 | 含义 |
|---|---|
| FREEZE | 冻结库存 |
| RELEASE | 释放库存 |
| DEDUCT | 正式扣减库存 |

---

# 7. 推荐索引优化（V2）

---

## t_order

```sql
CREATE INDEX idx_order_status
ON t_order(order_status);
```

---

## t_order_item

```sql
CREATE INDEX idx_order_id
ON t_order_item(order_id);
```

---

## t_inventory_record

```sql
CREATE INDEX idx_order_product
ON t_inventory_record(order_id, product_id);
```

---

# 8. 后续扩展字段（暂不实现）

---

# t_product

未来可以增加：

```sql
occupy_stock      INT COMMENT '占用库存'
transit_stock     INT COMMENT '在途库存'
safe_stock        INT COMMENT '安全库存'
```

---

# t_order

未来可以增加：

```sql
pay_time          DATETIME COMMENT '支付时间'
close_time        DATETIME COMMENT '关闭时间'
```

---

# t_inventory_record

未来可以增加：

```sql
before_stock      INT COMMENT '操作前库存'
after_stock       INT COMMENT '操作后库存'
operator_name     VARCHAR(64) COMMENT '操作人'
```