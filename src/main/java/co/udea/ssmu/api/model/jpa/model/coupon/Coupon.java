package co.udea.ssmu.api.model.jpa.model.coupon;

import co.udea.ssmu.api.model.jpa.model.strategy.Strategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cupon")
public class Coupon{
    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @NotNull
    @Column(name = "estado_cupon")
    private String status;

    @NotNull
    @Column(name = "cantidad_disponible")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "codigo", nullable = false)
    private Strategy strategy;
    
    public Coupon() {
    }

    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}