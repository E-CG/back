package co.udea.ssmu.api.services.coupon.facade;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.ICouponMapper;
import co.udea.ssmu.api.model.jpa.model.Coupon;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import co.udea.ssmu.api.utils.common.CouponCodeBuilder;
import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponFacade {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponCodeBuilder couponBuilder;
    @Autowired
    private ICouponMapper couponMapper;

    public CouponDTO createCoupon(CouponDTO couponDTO) {
        // Creando el codigo del cupón
        StrategyDTO strategyDTO = couponDTO.getStrategy();
        couponDTO.setCode(couponBuilder.buildCodeCoupon(strategyDTO.getName()));

        LocalDate today = LocalDate.now();
        if (strategyDTO.getStartDate().isAfter(today)) {
            strategyDTO.setIsActive(false);
            // Si la fecha de inicio es posterior al día de hoy
            couponDTO.setStatus(CouponStatusEnum.INACTIVO);
        } else if (strategyDTO.getEndDate().isBefore(today)) {
            strategyDTO.setIsActive(false);
            // Si la fecha de fin es anterior al día de hoy, establecer el estado como
            // Caducado
            couponDTO.setStatus(CouponStatusEnum.CADUCADO);
        } else {
            strategyDTO.setIsActive(true);
            // Si la fecha de inicio es hoy o anterior, establecer el estado como Activo
            couponDTO.setStatus(CouponStatusEnum.ACTIVO);
        }
        Coupon coupon = couponMapper.toEntity(couponDTO);
        return couponMapper.toDto(couponService.saveCoupon(coupon));
    }

    public CouponDTO editCoupon(CouponDTO updatedCoupon) {
        Coupon coupon = couponMapper.toEntity(updatedCoupon);
        return couponMapper.toDto(couponService.editCoupon(coupon));
    }

    public CouponDTO findByCode(String code) {
        return couponMapper.toDto(couponService.findById(code));
    }
}