package co.udea.ssmu.api.model.jpa.dto;

import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
public class CouponDTO{
    private String code;

    @Nullable
    @Min(1)
    @Max(100)
    private Integer amountAvalaible;
    
    @NotNull(message = "Falta la cantidad de cupones")
    @Min(1)
    @Max(100)
    private Integer amountCreated;

    private CouponStatusEnum status;
    
    private StrategyDTO strategy;
}