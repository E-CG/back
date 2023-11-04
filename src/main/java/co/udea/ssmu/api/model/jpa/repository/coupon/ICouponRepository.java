package co.udea.ssmu.api.model.jpa.repository.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.udea.ssmu.api.model.jpa.model.Coupon;

@Repository
<<<<<<< HEAD
public interface ICouponRepository extends JpaRepository<Coupon, String>{
=======
public interface ICouponRepository extends JpaRepository<Coupon, Long>{
>>>>>>> 03ef6cdca11fa684e2aec62710d7d97a90bbd006
/*     //Cuenta la cantidad de cupones activos
    @Query("SELECT COUNT(c) FROM Coupon c WHERE c.status = Activo")
    long countActiveCoupons(); */
}
