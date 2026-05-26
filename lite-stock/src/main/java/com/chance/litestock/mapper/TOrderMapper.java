package com.chance.litestock.mapper;

import com.chance.litestock.domain.dao.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chance.litestock.enums.OrderStatusEnum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* @author 32166
* @description 针对表【t_order(订单表)】的数据库操作Mapper
* @createDate 2026-05-13 20:37:04
* @Entity com.chance.litestock.domain.dao.TOrder
*/
public interface TOrderMapper extends BaseMapper<TOrder> {

    /**
     * 修改订单状态
     *
     * @param orderId         订单ID
     * @param orderStatusEnum 订单状态
     */
    @Update("update t_order set order_status = #{orderStatusEnum} where id = #{orderId} and order_status = 'WAIT_PAY'")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatusEnum") OrderStatusEnum orderStatusEnum);
}




