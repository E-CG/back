package co.udea.ssmu.api.services.coupon.facade;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.coupon.CouponMapper;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import co.udea.ssmu.api.utils.CouponBuilder;
import co.udea.ssmu.api.utils.CouponStatus;

@Service
public class CouponFacade {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponBuilder couponBuilder;
    @Autowired
    private CouponMapper couponMapper;

    public CouponDTO createCoupon(CouponDTO couponDTO) {
        // Creando el codigo del cupón
        StrategyDTO strategyDTO = couponDTO.getIdStrategy();
        couponDTO.setCode(couponBuilder.buildCodeCoupon(strategyDTO.getName()));

        LocalDate today = LocalDate.now();
        if (strategyDTO.getStartDate().isAfter(today)) {
            // Si la fecha de inicio es posterior al día de hoy,
            strategyDTO.setIsActive(false);
            couponDTO.setStatus(CouponStatus.INACTIVO);
        } else if (strategyDTO.getEndDate().isBefore(today)){
            // Si la fecha de fin es anterior al día de hoy, establecer el estado como Caducado
            couponDTO.setStatus(CouponStatus.CADUCADO);
        }else{
            // Si la fecha de inicio es hoy o anterior, establecer el estado como Activo
            strategyDTO.setIsActive(true);
            couponDTO.setStatus(CouponStatus.ACTIVO);
        }
        
        return couponMapper.toDto(couponService.saveCoupon(couponMapper.toEntity(couponDTO)));
    }

    public CouponDTO update(CouponDTO coupon) {
        return couponMapper.toDto(couponService.updateCoupon(couponMapper.toEntity(coupon)));
    }
}
