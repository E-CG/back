package co.udea.ssmu.api.model.jpa.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CouponDTO{
    @NotNull(message = "El código del cupón no puede ser nulo")
    private String code;

    @NotNull(message = "Falta la cantidad de cupones")
    @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
    @Max(value = 100, message = "La cantidad no puede ser mayor a 100")
    private int amount;

    private String status;
    private StrategyDTO idStrategy;

    // Constructor
    public CouponDTO() {
    }

    // Getter and setter
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String estadoCupon) {
        this.status = estadoCupon;
    }

    public StrategyDTO getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(StrategyDTO idStrategy) {
        this.idStrategy = idStrategy;
    }
}
