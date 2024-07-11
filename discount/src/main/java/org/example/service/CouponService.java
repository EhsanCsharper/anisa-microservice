package org.example.service;

import org.example.domain.Coupon;
import org.example.dto.CouponDTO;

public interface CouponService {
    Coupon create(CouponDTO couponDTO);
    CouponDTO findByCode(String name);
}
