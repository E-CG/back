package co.udea.ssmu.api.model.jpa.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import lombok.*;

@Getter
@Setter
@ToString
public class CouponDTO{
    private String code;

    @Min(1)
    @Max(100)
    private Integer amountAvalaible;
    
    @NotNull(message = "Falta la cantidad de cupones")
    @Min(1)
    @Max(100)
    private Integer amountCreated;

    @NotNull(message = "Falta el estado del cupón")
    private CouponStatusEnum status;
    
    private StrategyDTO strategy;
}