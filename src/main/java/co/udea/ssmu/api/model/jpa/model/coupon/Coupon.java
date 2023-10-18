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
@Table(name = "coupon")
public class Coupon extends Strategy{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_strategy")
    private Strategy idStrategy;
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Strategy getStrategy() {
        return idStrategy;
    }
    
    public void setIdStrategy(Strategy idStrategy) {
        this.idStrategy = idStrategy;
    }
}