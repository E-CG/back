package co.udea.ssmu.api.model.jpa.repository.coupon;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.udea.ssmu.api.model.jpa.model.Coupon;

@Repository
public interface ICouponRepository extends JpaRepository<Coupon, String> {
    
    //Cuenta la cantidad de cupones activos
    /* @Query("SELECT COUNT(c) FROM Coupon c WHERE c.status = Activo")
    long countActiveCoupons(); */

    // Obtiene los cupones con los parametros limit y offset, ordenados por cantidad disponible
    @Query(value = "SELECT c FROM Coupon c ORDER BY c.amountAvalaible DESC")
    List<Coupon> findAllWithQueryParam(@Param("limit") int limit, @Param("offset") int offset);
}