package org.example.discount;

import org.springframework.stereotype.Component;

@Component
public class DiscountFallBack implements DiscountClient {
    @Override
    public CouponDTO findByCouponCode(String code) {
        return null;
    }
}
