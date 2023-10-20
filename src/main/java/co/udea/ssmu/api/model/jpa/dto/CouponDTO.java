package co.udea.ssmu.api.model.jpa.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class CouponDTO extends StrategyDTO{
    @NotEmpty(message = "Falta el código del cupón")
    private String code;
    @Min(1)
    @Max(100)
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
        this.status= estadoCupon;
    }

    public StrategyDTO getStrategy() {
        return idStrategy;
    }

    public void setStrategy(StrategyDTO idStrategy) {
        this.idStrategy = idStrategy;
    }
}
