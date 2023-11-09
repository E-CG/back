package co.udea.ssmu.api.model.jpa.dto;

import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
public class CouponDTO{
    private String code;

    @NotNull(message = "Falta la cantidad de cupones")
    @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
    @Max(value = 100, message = "La cantidad no puede ser mayor a 100")
    private Integer amount;

    @NotNull(message = "Falta el estado del cupón")
    private CouponStatusEnum status;
    
    private StrategyDTO strategy;
}