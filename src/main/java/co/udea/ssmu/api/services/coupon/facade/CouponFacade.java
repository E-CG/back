package co.udea.ssmu.api.services.coupon.facade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.ICouponMapper;
import co.udea.ssmu.api.model.jpa.model.Coupon;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import co.udea.ssmu.api.utils.common.CouponCodeBuilder;
import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import co.udea.ssmu.api.utils.common.StrategyUserTypeEnum;
import co.udea.ssmu.api.utils.exception.InvalidCouponAmount;
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

        // Estableciendo cantidad creada de cupones
        couponDTO.setAmountAvalaible(couponDTO.getAmountCreated());
        if (couponDTO.getAmountCreated() < 1 && couponDTO.getAmountCreated() > 100) {
            throw new InvalidCouponAmount("La cantidad de cupones debe estar entre 1 y 100");
        }

        LocalDateTime today = LocalDateTime.now();
        if (strategyDTO.getStartDate().isAfter(today)) {
            strategyDTO.setIsActive(false);
            // Si la fecha de inicio es posterior al día de hoy
            couponDTO.setStatus(CouponStatusEnum.INACTIVO);
        } else if (strategyDTO.getEndDate().isBefore(today)) {
            strategyDTO.setIsActive(false);
            // Si la fecha de fin es anterior al día de hoy, establecer el estado como Caducado
            couponDTO.setStatus(CouponStatusEnum.CADUCADO);
        } else {
            strategyDTO.setIsActive(true);
            // Si la fecha de inicio es hoy o anterior, establecer el estado como Activo
            couponDTO.setStatus(CouponStatusEnum.ACTIVO);
        }

        int userTypeEnum = strategyDTO.getUserType().getCode();
        if(userTypeEnum == 0){
            strategyDTO.setUserType(StrategyUserTypeEnum.FRECUENTE);
        }else if(userTypeEnum == 1){
            strategyDTO.setUserType(StrategyUserTypeEnum.OCACIONAL);
        }

        Coupon coupon = couponMapper.toEntity(couponDTO);
        return couponMapper.toDto(couponService.saveCoupon(coupon));
    }

    public Page<CouponDTO> findWithFilter(Pageable pageable) {
        return couponService.findWithFilter(pageable).map(couponMapper::toDto);
    }
    
    public CouponDTO findByCode(String code) {
        CouponDTO couponDTO = couponMapper.toDto(couponService.findById(code));
        return couponDTO;
    }

    public List<CouponDTO> findAll(){
        return couponMapper.toDto(couponService.findAll());
    }

    public CouponDTO editCoupon(CouponDTO updatedCoupon) {
        Coupon coupon = couponMapper.toEntity(updatedCoupon);
        return couponMapper.toDto(couponService.editCoupon(coupon));
    }

    public void updateCouponFields(CouponDTO existingCoupon, Map<String, Object> updates) {
        if (updates.containsKey("amount")) {
            existingCoupon.setAmountAvalaible((Integer) updates.get("amount"));
        }if (updates.containsKey("code")) {
            existingCoupon.setCode((String) updates.get("code"));
        }
        if (updates.containsKey("strategy")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> strategyUpdates = (Map<String, Object>) updates.get("strategy");
            StrategyDTO existingStrategy = existingCoupon.getStrategy();
            if (strategyUpdates.containsKey("description")) {
                existingStrategy.setDescription((String) strategyUpdates.get("description"));
            }
            if (strategyUpdates.containsKey("startDate")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startDate = LocalDateTime.parse((String) strategyUpdates.get("startDate"), formatter);
                existingStrategy.setStartDate(startDate);
            }
        }
        editCoupon(existingCoupon);
    }
}