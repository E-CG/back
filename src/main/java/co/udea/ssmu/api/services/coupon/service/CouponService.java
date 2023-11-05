package co.udea.ssmu.api.services.coupon.service;

import java.util.List;
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

    public List<Coupon> findAll(int limit, int offset) {
        if(limit != 0 && offset != 0 || limit > 0 && offset > 0){
            return couponRepository.findAllWithQueryParam(limit, offset);
        }else{
            return couponRepository.findAll();
        }
    }

    public Coupon editCoupon(Coupon coupon){
        return couponRepository.save(coupon);
    }

    public Coupon findById(String code) {
        return couponRepository.findById(code).orElse(null);
    }
}