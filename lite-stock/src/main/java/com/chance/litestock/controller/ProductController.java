package com.chance.litestock.controller;

import com.chance.litestock.domain.Result;
import com.chance.litestock.domain.dao.TProduct;
import com.chance.litestock.service.TProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
@Tag(name = "商品")
public class ProductController {

    private final TProductService tProductService;

    @GetMapping("querySingleProduct/{productName}")
    @Operation(summary = "查询单个商品")
    public ResponseEntity<Result<TProduct>> querySingleProduct(@PathVariable String productName) {
        return Result.ok(tProductService.querySingleProduct(productName));
    }

}
