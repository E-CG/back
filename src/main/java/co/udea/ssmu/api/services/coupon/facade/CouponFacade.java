package co.udea.ssmu.api.services.coupon.facade;

import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.coupon.CouponMapper;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import co.udea.ssmu.api.utils.CouponBuilder;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponFacade {
    private final CouponService couponService;
    private final CouponMapper couponMapper;
    private final CouponBuilder couponBuilder;

    public CouponFacade(CouponService couponService, CouponMapper couponMapper, CouponBuilder couponBuilder) {
        this.couponService = couponService;
        this.couponMapper = couponMapper;
        this.couponBuilder = couponBuilder;
    }

    public CouponDTO save(CouponDTO couponDTO) {
        // Generar un nuevo código si no se proporciona
        if (couponDTO.getCode() == null || couponDTO.getCode().isEmpty()) {
            // Obtener el objeto StrategyDTO de idStrategy
            StrategyDTO strategyDTO = couponDTO.getIdStrategy();

            if (strategyDTO != null && strategyDTO.getName() != null && !strategyDTO.getName().isEmpty()) {
                couponDTO.setCode(couponBuilder.buildCodeCoupon(strategyDTO.getName()));
            }
        }
        return couponMapper.toDto(couponService.saveCoupon(couponMapper.toEntity(couponDTO)));
    }

    public CouponDTO update(CouponDTO coupon) {
        return couponMapper.toDto(couponService.updateCoupon(couponMapper.toEntity(coupon)));
    }
}
