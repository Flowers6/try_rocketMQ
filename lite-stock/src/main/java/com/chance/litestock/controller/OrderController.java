package com.chance.litestock.controller;

import com.chance.litestock.domain.Result;
import com.chance.litestock.domain.dto.CreateOrder;
import com.chance.litestock.service.TOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Tag(name = "订单")
public class OrderController {

    private final TOrderService tOrderService;

    @PostMapping("create")
    @Operation(summary = "创建订单")
    public ResponseEntity<Result<Void>> create(@RequestBody CreateOrder createOrder) {
        tOrderService.createOrder(createOrder);
        return Result.ok();
    }

}
