package co.udea.ssmu.api.services.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.udea.ssmu.api.model.jpa.model.Coupon;
import co.udea.ssmu.api.model.jpa.repository.coupon.ICouponRepository;

@Service
@Transactional
public class CouponService {
    @Autowired
    private ICouponRepository couponRepository;

    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(Coupon coupon){
        Coupon existCoupon = couponRepository.findById(coupon.getCode()).orElse(null);
        existCoupon.setCode(coupon.getCode());
        existCoupon.setAmount(coupon.getAmount());
        existCoupon.setStatus(coupon.getStatus());
        return couponRepository.save(existCoupon);
    }
}