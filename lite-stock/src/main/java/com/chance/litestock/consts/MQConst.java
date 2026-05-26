package com.chance.litestock.consts;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description : Message Queue Constants
 * @date : 2026/5/26
 * @time : 14:56
 */
public final class MQConst {

    // ====================================== topic ======================================
    public static final String ORDER_TOPIC = "order-topic";
    // ====================================== topic ======================================

    // ====================================== consumer-group ======================================
    public static final String ORDER_TIMEOUT_CONSUMER = "order-timeout-consumer";
    // ====================================== consumer-group ======================================

    // ====================================== tag ======================================
    public static final String ORDER_TIMEOUT_TAG = "order-timeout-tag";

    public static final long ORDER_TIMEOUT_DELAY = 30 * 60 * 1000;
    // ====================================== tag ======================================

    // ====================================== await-duration ======================================
    // ====================================== await-duration ======================================

    // ====================================== request-timeout ======================================
    // ====================================== request-timeout ======================================

}
