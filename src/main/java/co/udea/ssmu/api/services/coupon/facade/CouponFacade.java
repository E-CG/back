package co.udea.ssmu.api.services.coupon.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.mapper.coupon.CouponMapper;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponFacade {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponMapper couponMapper;

    public CouponDTO saveCoupon(CouponDTO coupon){
        return couponMapper.toDto(couponService.saveCoupon(couponMapper.toEntity(coupon)));
    }

    public List<CouponDTO> findByAll() {
        List<Coupon> coupons = couponService.findByAll();
        return couponMapper.toDto(coupons);
    }
    public CouponDTO update(CouponDTO coupon){
        return couponMapper.toDto(couponService.updateCoupon(couponMapper.toEntity(coupon)));
    }

}
