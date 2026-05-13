DROP TABLE IF EXISTS t_product;

CREATE TABLE t_product
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    product_name    VARCHAR(100) NOT NULL COMMENT '商品名称',

    available_stock INT          NOT NULL DEFAULT 0 COMMENT '可用库存',

    frozen_stock    INT          NOT NULL DEFAULT 0 COMMENT '冻结库存',

    version         INT          NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',

    create_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    update_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='商品表';

DROP TABLE IF EXISTS t_order;

CREATE TABLE t_order
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    order_no     VARCHAR(64)    NOT NULL UNIQUE COMMENT '订单号',

    order_status VARCHAR(32)    NOT NULL COMMENT '订单状态',

    total_amount DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '订单总金额',

    create_time  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    update_time  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='订单表';

DROP TABLE IF EXISTS t_order_item;

CREATE TABLE t_order_item
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    order_id    BIGINT   NOT NULL COMMENT '订单ID',

    product_id  BIGINT   NOT NULL COMMENT '商品ID',

    quantity    INT      NOT NULL COMMENT '购买数量',

    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='订单明细表';

DROP TABLE IF EXISTS t_inventory_record;

CREATE TABLE t_inventory_record
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',

    order_id     BIGINT      NOT NULL COMMENT '订单ID',

    product_id   BIGINT      NOT NULL COMMENT '商品ID',

    operate_type VARCHAR(32) NOT NULL COMMENT '库存操作类型',

    quantity     INT         NOT NULL COMMENT '操作数量',

    remark       VARCHAR(255)         DEFAULT NULL COMMENT '备注',

    create_time  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='库存流水表';

INSERT INTO t_product
    (product_name, available_stock, frozen_stock)
VALUES ('MacBook Pro', 100, 0);

INSERT INTO t_product
    (product_name, available_stock, frozen_stock)
VALUES ('iPhone 16', 200, 0);

INSERT INTO t_product
    (product_name, available_stock, frozen_stock)
VALUES ('AirPods Pro', 300, 0);