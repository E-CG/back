package co.udea.ssmu.api.services.coupon.facade;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.coupon.CouponMapper;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import co.udea.ssmu.api.utils.CouponBuilder;
import co.udea.ssmu.api.utils.CouponStatus;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponFacade {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponBuilder couponBuilder;
    @Autowired
    private CouponMapper couponMapper;

    public CouponDTO createCoupon(CouponDTO couponDTO) {
        // Creando el codigo del cupón
        StrategyDTO strategyDTO = couponDTO.getStrategy();
        couponDTO.setCode(couponBuilder.buildCodeCoupon(strategyDTO.getName()));

        LocalDate today = LocalDate.now();
        if (strategyDTO.getStartDate().isAfter(today)) {
            strategyDTO.setIsActive(false);
            // Si la fecha de inicio es posterior al día de hoy
            couponDTO.setStatus(CouponStatus.INACTIVO);
        } else if (strategyDTO.getEndDate().isBefore(today)) {
            strategyDTO.setIsActive(false);
            // Si la fecha de fin es anterior al día de hoy, establecer el estado como Caducado
            couponDTO.setStatus(CouponStatus.CADUCADO);
        } else {
            strategyDTO.setIsActive(true);
            // Si la fecha de inicio es hoy o anterior, establecer el estado como Activo
            couponDTO.setStatus(CouponStatus.ACTIVO);
        }
        Coupon coupon = couponMapper.toEntity(couponDTO);
        return couponMapper.toDto(couponService.saveCoupon(coupon));
    }

    public CouponDTO update(CouponDTO coupon) {
        return couponMapper.toDto(couponService.updateCoupon(couponMapper.toEntity(coupon)));
    }
}
