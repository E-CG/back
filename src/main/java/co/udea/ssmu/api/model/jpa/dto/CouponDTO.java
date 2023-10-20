package co.udea.ssmu.api.model.jpa.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class CouponDTO {
    @NotEmpty(message = "Falta el código del cupón")
    private String code;
    @Min(1)
    @Max(100)
    private int quantity;
    private String status;
    
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
    
    public int getCantidadDisponible() {
        return quantity;
    }
    
    public void setCantidadDisponible(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String estadoCupon) {
        this.status= estadoCupon;
    }
}
