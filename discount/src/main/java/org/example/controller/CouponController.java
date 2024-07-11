package org.example.controller;

import org.example.dto.CouponDTO;
import org.example.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public CouponDTO create(@RequestBody CouponDTO couponDTO) {
        couponService.create(couponDTO);
        return couponDTO;
    }

    @GetMapping("/{code}")
    public ResponseEntity<CouponDTO> findByCode(@PathVariable String code) {
        return new ResponseEntity<>(couponService.findByCode(code), HttpStatus.OK);
    }
}
