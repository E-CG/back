package co.udea.ssmu.api.model.jpa.repository.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, String>{
    //Aquí se deben agregar las consultas(Querys) personalizadas
}
