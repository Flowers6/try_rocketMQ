package com.chance.litestock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chance.litestock.domain.dao.TOrderItem;
import com.chance.litestock.service.TOrderItemService;
import com.chance.litestock.mapper.TOrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author 32166
* @description 针对表【t_order_item(订单明细表)】的数据库操作Service实现
* @createDate 2026-05-13 20:37:04
*/
@Service
public class TOrderItemServiceImpl extends ServiceImpl<TOrderItemMapper, TOrderItem>
    implements TOrderItemService{

}




