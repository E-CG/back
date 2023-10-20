package co.udea.ssmu.api.services.coupon.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;
import co.udea.ssmu.api.model.jpa.repository.coupon.CouponRepository;
import co.udea.ssmu.api.utils.CouponBuilder;
import co.udea.ssmu.api.utils.common.Messages;
import co.udea.ssmu.api.utils.exception.BusinessException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponService {
    @Autowired
    private final CouponRepository couponRepository;
    private final Messages messages;
    private CouponBuilder couponBuilder;

    public CouponService(CouponRepository couponRepository, Messages messages) {
        this.couponRepository = couponRepository;
        this.messages = messages;
    }

    public Coupon saveCoupon(Coupon coupon) {
        String couponCode = couponBuilder.buildCodeCoupon(coupon.getName());

        LocalDate today = LocalDate.now();
        if (coupon.getStartDate().isAfter(today)) {
            coupon.setStatus("Inactivo");
        } else if (coupon.getEndDate().isBefore(today)) {
            coupon.setStatus("Caducado");
        } else {
            coupon.setStatus("Activo");
        }

        Optional<Coupon> couponOptional = couponRepository.findById(couponCode);
        coupon.setCode(couponCode);
        if (couponOptional.isPresent()) {
            throw new BusinessException(String.format(messages.get("El código del cupón ya existe"),coupon.getCode(), coupon.getName()));
        }
        // cuponRepository es una interfaz que hereda de JpaRepository, por tanto, sus métodos
        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(Coupon coupon) {
        Optional<Coupon> couponOptional = couponRepository.findById(coupon.getCode());
        if (couponOptional.isEmpty()) {
            throw new BusinessException(String.format(messages.get("El cupón no existe")));
        }
        return couponRepository.save(coupon);
    }

}