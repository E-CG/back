package co.udea.ssmu.api.model.jpa.dto;

import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
<<<<<<< HEAD
public class CouponDTO {
=======
public class CouponDTO{
    private long id_coupon;

>>>>>>> 03ef6cdca11fa684e2aec62710d7d97a90bbd006
    private String code;

    @NotNull(message = "Falta la cantidad de cupones")
    @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
    @Max(value = 100, message = "La cantidad no puede ser mayor a 100")
    private int amount;

    private CouponStatusEnum status;

    private StrategyDTO strategy;
}
