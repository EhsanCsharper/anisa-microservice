package org.example.service;

import org.example.domain.Coupon;
import org.example.dto.CouponDTO;
import org.example.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Coupon create(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponDTO, coupon);
        return couponRepository.save(coupon);
    }

    @Override
    public CouponDTO findByCode(String name) {
        Optional<Coupon> optionalCoupon = couponRepository.findByCode(name);
        if (optionalCoupon.isEmpty())
            throw new RuntimeException("not found");
        CouponDTO couponDTO = new CouponDTO();
        BeanUtils.copyProperties(optionalCoupon.get(), couponDTO);
        return couponDTO;
    }
}
