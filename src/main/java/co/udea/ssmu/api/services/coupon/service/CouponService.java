package co.udea.ssmu.api.services.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Coupon> findWithFilter(Pageable pageable) {
        return couponRepository.findAll(pageable);
    }

    public List<Coupon> findAll(){
        return couponRepository.findAll();
    }

    public Coupon editCoupon(Coupon coupon){
        return couponRepository.save(coupon);
    }

    public Coupon findById(String code) {
        return couponRepository.findById(code).orElse(null);
    }
}