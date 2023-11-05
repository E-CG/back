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

    public Coupon editCoupon(Coupon coupon){
        return couponRepository.save(coupon);
    }

    public Coupon findById(String code) {
        return couponRepository.findById(code).orElse(null);
    }
}