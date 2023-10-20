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
    private final CouponService couponService;
    @Autowired
    private final CouponMapper couponMapper;
    
    public CouponFacade(CouponService couponService, CouponMapper couponMapper) {
        this.couponService = couponService;
        this.couponMapper = couponMapper;
    }

    public CouponDTO saveCoupon(CouponDTO coupon){
        return couponMapper.toDto(couponService.saveCoupon(couponMapper.toEntity(coupon)));
    }

}
